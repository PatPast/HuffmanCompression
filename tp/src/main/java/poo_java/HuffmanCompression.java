package poo_java;

import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.BitSet;

public class HuffmanCompression implements Compression {
    private Hashtable<Character, String> charToCode; // Associe un caractère à un code
    private Hashtable<String, Character> codeToChar; // Associe un code binaire à un caractère

    /**
     * Compresse une chaîne de caractères en utilisant l'algorithme de compression de Huffman.
     *
     * @param data La chaîne de caractères à compresser.
     * @return Un tableau de bytes représentant les données compressées.
     */

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

        // Génération de l'arbre de Huffman
        HuffmanNode huffmanTree = this.buildHuffmanTree(ht);
        generateCodes(huffmanTree, "");

    
        bitPointer = writeMetadatas(data, compressedData);
        System.out.println("bitPointer : " + bitPointer);

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

    /**
     * Décompresse un tableau de bytes en utilisant l'algorithme de compression de Huffman.
     *
     * @param data Le tableau de bytes à décompresser.
     * @return La chaîne de caractères décompressée.
     */
    @Override
    public String decompress(byte[] data) {
    // Implémentation de la décompression
        int i, j;
        BitSet compressedData = BitSet.valueOf(data);
        StringBuffer  decompressedData = new StringBuffer();
        int bitPointer = 0;   
        int dataSize = 0;
        String code = ""; 
        this.codeToChar = new Hashtable<String, Character>();

        int[] res = readMetadatas(compressedData);
        dataSize = res[0];
        bitPointer = res[1];
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

    private int writeMetadatas(String data, BitSet bits){
    // Ecriture des métadonnées (taille de la table de hashage, table de hashage (caractère, code binaire), nombre de caractères) 

        char[] bitTab; // tableau de bits (sous forme de caractères)
        int i; 
        int plageBits; // nombre de bits à écrire
        int bitPointer = 0; // index du bit courant

        // taille de la table de hashage (caractère, code binaire) codée sur 24 bits
        plageBits = 24;

        bitTab = String.format("%"+plageBits+"s", 
                Integer.toBinaryString(charToCode.size()))
                .replace(' ', '0')
                .toCharArray();
        for (i = 0; i < plageBits; i++)
            if (bitTab[i] == '1')
                bits.set(bitPointer + (plageBits-1-i));
        bitPointer += plageBits;
        
        // table de hashage (caractère, code binaire)
        for (Character htCharacter : charToCode.keySet()) {
        // caractère codé sur 24 bits
            plageBits = 24;
            bitTab = String.format("%"+plageBits+"s", 
                    Integer.toBinaryString(htCharacter))
                    .replace(' ', '0')
                    .toCharArray();
            for (i = 0; i < plageBits; i++)
                if (bitTab[i] == '1')
                    bits.set(bitPointer + (plageBits-1-i));
            bitPointer += plageBits;

        // taille du code binaire du caractère codé sur 8 bits
            plageBits = 8;
            bitTab = String.format("%"+plageBits+"s", 
                    Integer.toBinaryString(charToCode.get(htCharacter).length()))
                    .replace(' ', '0')
                    .toCharArray();
            for (i = 0; i < plageBits; i++)
                if (bitTab[i] == '1')
                    bits.set(bitPointer + (plageBits-1-i));
            bitPointer += plageBits;
            
        // code binaire du caractère
            bitTab = charToCode
                    .get(htCharacter)
                    .toCharArray();
            for (i = 0; i < bitTab.length; i++)
                if (bitTab[i] == '1')
                    bits.set(bitPointer + i);
            bitPointer += bitTab.length;
        }

        // nombre de caractère de ce fichier, codé sur 32 bits
        plageBits = 32;
        bitTab = String.format("%"+plageBits+"s", 
                Integer.toBinaryString(data.length()))
                .replace(' ', '0')
                .toCharArray();
        System.out.print("Nombre de caractères : ");
        System.out.println(bitTab);
        for (i = 0; i < plageBits; i++)
            if (bitTab[i] == '1')
                bits.set(bitPointer + (plageBits-1-i));
        bitPointer += plageBits;

        return bitPointer;
    }

    private int[] readMetadatas(BitSet bits){
    // Lecture des métadonnées (taille de la table de hashage, table de hashage (caractère, code binaire), nombre de caractères)
        int i, j;
        int hashTableSize = 0;
        int codeSize = 0;
        int dataSize = 0;
        String tempStr = "";
        int plageBits;
        int bitPointer = 0; // index du bit courant
        this.codeToChar = new Hashtable<String, Character>();

        // taille de la table de hashage (caractère, code binaire) codée sur 24 bits
        plageBits = 24;
        for (i = 0; i < plageBits; i++){
            if (bits.get(bitPointer + i))
                hashTableSize += Math.pow(2, i);
        }
        bitPointer += plageBits;

        // table de hashage (caractère, code binaire)
        for (i = 0; i < hashTableSize; i++) {
            char key = 0;
            // caractère codé sur 24 bits
            plageBits = 24;
            for (j = 0; j < plageBits; j++){
                if (bits.get(bitPointer + j))
                    key += Math.pow(2, j);
            }
            bitPointer += plageBits;

            // taille du code binaire du caractère codé sur 8 bits
            plageBits = 8;
            codeSize = 0;
            for (j = 0; j < plageBits; j++){
                if (bits.get(bitPointer + j))
                    codeSize += Math.pow(2, j);
            }
            bitPointer += plageBits;

            // code binaire du caractère
            tempStr = "";
            for (j = 0; j < codeSize; j++){
                if (bits.get(bitPointer + j))
                    tempStr += "1";
                else
                    tempStr += "0";
            }
            bitPointer += codeSize;
            this.codeToChar.put(tempStr, key);
        }
        // nombre de caractère de ce fichier, codé sur 32 bits
        plageBits = 32;
        for (i = 0; i < plageBits; i++){
            if (bits.get(bitPointer + i))
                dataSize += Math.pow(2, i);
        }
        bitPointer += plageBits;
        return new int[]{dataSize, bitPointer};
    }
        
    private void generateCodes(HuffmanNode node, String code) {
        HuffmanTuple pair = node.getValue();
        
    // Génère les codes binaires pour chaque caractère
        if (node.isLeaf()) {
            this.charToCode.put(pair.getCaractere(), code);
        }
        else {
            this.generateCodes((HuffmanNode)node.getLeft(), code + "0");
            this.generateCodes((HuffmanNode)node.getRight(), code + "1");
        }
    }

    private HuffmanNode buildHuffmanTree(Hashtable<Character, Integer> frequencyTable) {
    // Construction de l’arbre de Huffman
        HuffmanNode newNode = null;
        PriorityQueue<HuffmanNode> nodes = new PriorityQueue<HuffmanNode>();
        for (Character car : frequencyTable.keySet()) {
            HuffmanTuple pair = new HuffmanTuple(car, frequencyTable.get(car));
            HuffmanNode node = new HuffmanNode(pair);
            nodes.add(node);
        }
        while (nodes.size() > 1) {
            HuffmanNode left = nodes.poll();
            HuffmanNode right = nodes.poll();
            newNode = new HuffmanNode(left, right);
            nodes.add(newNode);
        }
        System.out.println(newNode);
        return newNode;
    }
}
