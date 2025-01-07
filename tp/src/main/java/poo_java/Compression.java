public interface Compression {
    byte[] compress(String data);
    String decompress(byte[] data);
}
