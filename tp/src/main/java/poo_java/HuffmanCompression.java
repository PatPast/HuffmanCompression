package poo_java;


import java.util.Hashtable;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;

public class HuffmanCompression implements Compression {
    private Hashtable<Character, String> charToCode; // Associe un caractère à un cod
    private Hashtable<String, Character> codeToChar; // Associe un code binaire à un
    @Override
    public byte[] compress(String data) {
        int i, j;
        BitSet compressedData = new BitSet();
        char[] bitTab; // tableau de bits
        int bitPointer = 0; // index du bit courant
        this.charToCode = new Hashtable<Character, String>();

    // Implémentation de la compression
        Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
        for (i = 0; i < data.length(); i++) {
            Integer n_occurrences = ht.get(data.charAt(i));
            if (n_occurrences == null)
                ht.put(data.charAt(i), 1);
            else
                ht.put(data.charAt(i), n_occurrences + 1);
        }
        

        // // Affichage de la table de fréquence
        // for (Enumeration<Character> e = ht.keys(); e.hasMoreElements();) {
        //     Character key = e.nextElement() ;
        //     System.out.println(key + " : " + ht.get(key));
        // }


        // Génération de l'arbre de Huffman
        HuffmanNode huffmanTree = this.buildHuffmanTree(ht);
        generateCodes(huffmanTree, "");

    // Ecriture des métadonnées (taille de la table de hashage, table de hashage (caractère, code binaire), nombre de caractères)

        // taille de la table de hashage (caractère, code binaire) codée sur 24 bits
        String tempStr = String.format("%24s", 
            Integer.toBinaryString(charToCode.size()))
            .replace(' ', '0');
        bitTab = tempStr.toString().toCharArray();
        for (i = 0; i < bitTab.length; i++)
            if (bitTab[i] == '1')
                compressedData.set(bitPointer + (23-i));
        bitPointer += bitTab.length;
        
        // table de hashage (caractère, code binaire)
        for (Enumeration<Character> e = charToCode.keys(); e.hasMoreElements();) {
            Character key = e.nextElement();
        // caractère codé sur 24 bits
            tempStr = String.format("%24s", 
                Integer.toBinaryString(key)
            ).replace(' ', '0');
            bitTab = tempStr.toString().toCharArray();
            System.out.print("--> " + key + " : ");
            System.out.println(bitTab);
            for (i = 0; i < 24; i++)
                if (bitTab[i] == '1')
                    compressedData.set(bitPointer + (23-i));
            bitPointer += 24;

        // taille du code binaire du caractère codé sur 8 bits
            tempStr = String.format("%8s", 
                Integer.toBinaryString(charToCode.get(key).length())
            ).replace(' ', '0');
            bitTab = tempStr.toString().toCharArray();
            System.out.print("Taille du code binaire de " + key + " : ");
            System.out.println(bitTab);
            for (i = 0; i < 8; i++)
                if (bitTab[i] == '1')
                    compressedData.set(bitPointer + (7-i));
            bitPointer += 8;
            
        // code binaire du caractère
            bitTab = charToCode.get(key).toCharArray();
            System.out.print("Code binaire de " + key + " : ");
            System.out.println(bitTab);
            for (i = 0; i < bitTab.length; i++)
                if (bitTab[i] == '1')
                    compressedData.set(bitPointer + i);
            bitPointer += bitTab.length;
        }

        // nombre de caractère de ce fichier, codé sur 32 bits
        tempStr = String.format("%32s", 
            Integer.toBinaryString(data.length())
        ).replace(' ', '0');
        bitTab = tempStr.toString().toCharArray();
        System.out.print("Nombre de caractères : ");
        System.out.println(bitTab);
        for (i = 0; i < 32; i++)
            if (bitTab[i] == '1')
                compressedData.set(bitPointer + (31-i));
        bitPointer += 32;

        // Codes binaires compressé des caractères du fichier
        for (i = 0; i < data.length(); i++) {
            // Récupère le code binaire du caractère
            bitTab = this.charToCode.get(data.charAt(i)).toCharArray();
            for (j = 0; j < bitTab.length; j++) {
                if (bitTab[j] == '1')
                    compressedData.set(bitPointer + j);
            }
            bitPointer += bitTab.length;
        }
        
        return compressedData.toByteArray();
    
    }
    @Override
    public String decompress(byte[] data) {
    // Implémentation de la décompression
        int i, j;
        BitSet compressedData = BitSet.valueOf(data);
        StringBuffer  decompressedData = new StringBuffer();
        int bitPointer = 0;   
        int hashTableSize = 0;
        int codeSize = 0;
        int dataSize = 0;
        String code = ""; 
        String tempStr = "";
        this.codeToChar = new Hashtable<String, Character>();

    // Lecture des métadonnées (taille de la table de hashage, table de hashage (caractère, code binaire), nombre de caractères)
        // taille de la table de hashage (caractère, code binaire) codée sur 24 bits
        for (i = 0; i < 24; i++){
            if (compressedData.get(bitPointer + i))
                hashTableSize += Math.pow(2, i);
        }
        bitPointer += 24;

        // table de hashage (caractère, code binaire)
        for (i = 0; i < hashTableSize; i++) {
            char key = 0;
            // caractère codé sur 24 bits
            for (j = 0; j < 24; j++){
                if (compressedData.get(bitPointer + j))
                    key += Math.pow(2, j);
            }
            bitPointer += 24;

            // taille du code binaire du caractère codé sur 8 bits
            codeSize = 0;
            for (j = 0; j < 8; j++){
                if (compressedData.get(bitPointer + j))
                    codeSize += Math.pow(2, j);
            }
            bitPointer += 8;

            // code binaire du caractère
            tempStr = "";
            for (j = 0; j < codeSize; j++){
                if (compressedData.get(bitPointer + j))
                    tempStr += "1";
                else
                    tempStr += "0";
            }
            bitPointer += codeSize;
            this.codeToChar.put(tempStr, key);
        }

        // nombre de caractère de ce fichier, codé sur 32 bits    
        for (i = 0; i < 32; i++){
            if (compressedData.get(bitPointer + i))
                dataSize += Math.pow(2, i);
        }
        bitPointer += 32;
        
    // Décompression à l'aide des métadonnées
        i = j = 0;
        while(j < dataSize) {
            if (compressedData.get(bitPointer + i))
                code += "1";
            else
                code += "0";
            if (this.codeToChar.containsKey(code)) {
                decompressedData.append(this.codeToChar.get(code));
                code = "";
                j++;
            }
            i++;
        }

        return decompressedData.toString();
    }
    private void generateCodes(HuffmanNode node, String code) {
        ImmutablePair<Character, Integer> pair = (ImmutablePair<Character, Integer>)node.getValue();
        
    // Génère les codes binaires pour chaque caractère
        if (node.isLeaf()) {
            this.charToCode.put(pair.getKey(), code);
        }
        else {
            this.generateCodes((HuffmanNode)node.getLeft(), code + "0");
            this.generateCodes((HuffmanNode)node.getRight(), code + "1");
        }
    }
    private HuffmanNode buildHuffmanTree(Hashtable<Character, Integer> frequencyTable) {
    // Construction de l’arbre de Huffman
        HuffmanNode newNode = null;
        ArrayList<Character> characters = new ArrayList<Character>(frequencyTable.keySet());
        // ArrayList<Integer> frequencies = new ArrayList<Integer>(frequencyTable.values());
        ArrayList<HuffmanNode> nodes = new ArrayList<HuffmanNode>();
        for (Character car : characters) {
            ImmutablePair<Character, Integer> pair = new ImmutablePair<Character, Integer>(car, frequencyTable.get(car));
            nodes.add(new HuffmanNode(pair));
        }
        while (nodes.size() != 1) {
            Collections.sort(nodes);
            ImmutablePair<Character, Integer> pair = new ImmutablePair<Character, Integer>(null, 
                ((ImmutablePair<Character, Integer>)nodes.get(0).getValue()).getValue() + 
                ((ImmutablePair<Character, Integer>)nodes.get(1).getValue()).getValue());
            newNode = new HuffmanNode(pair);
            newNode.setLeft(nodes.get(0));
            newNode.setRight(nodes.get(1));
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(newNode);
        }
        System.out.println(newNode);
        return newNode;
    }
}
