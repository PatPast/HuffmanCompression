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
    // Implémentation de la compression
        Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
        for (int i = 0; i < data.length(); i++) {
            Integer n_occurrences = ht.get(data.charAt(i));
            if (n_occurrences == null)
                ht.put(data.charAt(i), 1);
            else
                ht.put(data.charAt(i), n_occurrences + 1);
        }
        

        // Affichage de la table de fréquence
        for (Enumeration<Character> e = ht.keys(); e.hasMoreElements();) {
            Character key = e.nextElement() ;
            System.out.println(key + " : " + ht.get(key));
        }

        
        // Génération de l'arbre de Huffman
        HuffmanNode huffmanTree = this.buildHuffmanTree(ht);
        generateCodes(huffmanTree, "");

        BitSet compressedData = new BitSet();

        char[] code; // tableau de bits d'un code 
        for (int i = 0, j = 0; i < data.length(); i++, j += code.length) {
            // Récupère le code binaire du caractère
            System.err.println(this.charToCode.get('a'));
            code = this.charToCode.get(data.charAt(i)).toCharArray(); 
            for (int k = 0; k < code.length; k++) {
                if (code[k] == '1')
                    compressedData.set(j + k);
            }
        }


        return compressedData.toByteArray();
    }
    @Override
    public String decompress(byte[] data) {
    // Implémentation de la décompression
        return null;
    }
    private void generateCodes(HuffmanNode node, String code) {

        this.charToCode = new Hashtable<Character, String>();
        this.codeToChar = new Hashtable<String, Character>();
    // Génère les codes binaires pour chaque caractère
        if (node.isLeaf()) {
            this.charToCode.put(((ImmutablePair<Character, Integer>)node.getValue()).getKey(), code);
            this.codeToChar.put(code, ((ImmutablePair<Character, Integer>)node.getValue()).getKey());
            System.out.println("Code de " + ((ImmutablePair<Character, Integer>)node.getValue()).getKey() + " : " + code);
            char test = ((ImmutablePair<Character, Integer>)node.getValue()).getKey();
            System.out.println(this.charToCode.get(((ImmutablePair<Character, Integer>)node.getValue()).getKey()));
            System.out.println(this.charToCode.get(test));
            // TODO corriger le bug du code de 'a' qui retourne null
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
