package poo_java;


import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Collections;

public class HuffmanCompression implements Compression {
    private Hashtable<Character, String> charToCode; // Associe un caractère à un cod
    private Hashtable<String, Character> codeToChar; // Associe un code binaire à un
    @Override
    public byte[] compress(String data) {
    // Implémentation de la compression
        Hashtable ht = new Hashtable();
        for (int i = 0; i < data.length(); i++) {
            Integer n_occurrences = (Integer)ht.get(data.charAt(i));
            if (n_occurrences == null)
                ht.put(data.charAt(i), 1);
            else
                ht.put(data.charAt(i), n_occurrences + 1);
        }
        for (Enumeration e = ht.keys(); e.hasMoreElements();) {
            Character key = (Character) e.nextElement() ;
            System.out.println(key + " : " + (Integer)ht.get(key));
        }
        this.buildHuffmanTree(ht);
        return null;
    }
    @Override
    public String decompress(byte[] data) {
    // Implémentation de la décompression
        return null;
    }
    // private void generateCodes(HuffmanNode root, String code) {
    // // Récursion pour générer les codes binaires
    // }
    private HuffmanNode buildHuffmanTree(Hashtable<Character, Integer> frequencyTable) {
    // Construction de l’arbre de Huffman
        HuffmanNode newNode = null;
        ArrayList<Integer> frequencies = new ArrayList<Integer>(frequencyTable.values());
        ArrayList<HuffmanNode> nodes = new ArrayList<HuffmanNode>();
        for (Integer freq : frequencies) {
            nodes.add(new HuffmanNode(freq));
        }
        while (nodes.size() != 1) {
            Collections.sort(nodes);
            newNode = new HuffmanNode((Integer)nodes.get(0).value + (Integer)nodes.get(1).value);
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
