import java.util.Hashtable;

public class HuffmanCompression implements Compression {
    private Hashtable<Character, String> charToCode; // Associe un caractère à un cod
    private Hashtable<String, Character> codeToChar; // Associe un code binaire à un
    @Override
    public byte[] compress(String data) {
    // Implémentation de la compression
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
