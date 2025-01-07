import java.util.Hashtable;
import java.util.Enumeration;

public class HuffmanCompression implements Compression {
    private Hashtable<Character, String> charToCode; // Associe un caractère à un cod
    private Hashtable<String, Character> codeToChar; // Associe un code binaire à un
    @Override
    public byte[] compress(String data) {
    // Implémentation de la compression
        Hashtable dictionnaire = new Hashtable();
        for (int i = 0; i < data.length(); i++) {
            Integer n_occurrences = (Integer)dictionnaire.get(data.charAt(i));
            if (n_occurrences == null)
                dictionnaire.put(data.charAt(i), 1);
            else
                dictionnaire.put(data.charAt(i), n_occurrences + 1);
        }
        for (Enumeration e = dictionnaire.keys(); e.hasMoreElements();) {
            Character key = (Character) e.nextElement() ;
            System.out.println(key + " : " + (Integer)dictionnaire.get(key));
        }
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
    // private HuffmanNode buildHuffmanTree(Hashtable<Character, Integer> frequencyTable) {
    // // Construction de l’arbre de Huffman
    // }
}
