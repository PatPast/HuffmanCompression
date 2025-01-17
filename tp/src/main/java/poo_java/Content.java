public interface Content {
    byte[] compress();
    void decompress(byte[] data);
}
