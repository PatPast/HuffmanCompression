package poo_java;

public class TextContent implements Content {
    private String text;

    public TextContent(String text) {
        this.text = text;
    }

    @Override
    public byte[] compress() {
        // Implémentation simplifiée (réutiliser Huffman du TP3)
        HuffmanCompression compressor = new HuffmanCompression();
        return compressor.compress(text);
    }
    @Override
    public void decompress(byte[] data) {
        // Décompression avec Huffman
        HuffmanCompression compressor = new HuffmanCompression();
        this.text = compressor.decompress(data);
    }
    public String getText() {
        return text;
    }
}
