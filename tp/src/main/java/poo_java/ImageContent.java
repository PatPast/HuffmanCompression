package poo_java;

public class ImageContent implements Content {
    private BufferedImage buffer;

    public ImageContent(BufferedImage buffer) {
        this.buffer = buffer;
    }

    @Override
    public byte[] compress() {
        // // Implémentation simplifiée (réutiliser Huffman du TP3)
        // HuffmanCompression compressor = new HuffmanCompression();
        // return compressor.compress(text);
        return null;
    }
    @Override
    public void decompress(byte[] data) {
        // Décompression avec Huffman
        // HuffmanCompression compressor = new HuffmanCompression();
        // this.text = compressor.decompress(data);
    }
    public BufferedImage getBuffer() {
        return this.buffer;
    }
}
