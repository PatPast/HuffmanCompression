package poo_java;

/**
 * Interface représentant un algorithme de compression.
 */
public interface Compression {
    /**
     * Compresse une chaîne de caractères.
     *
     * @param data La chaîne de caractères à compresser.
     * @return Un tableau de bytes représentant les données compressées.
     */
    byte[] compress(String data);

    /**
     * Décompresse un tableau de bytes.
     * 
     * @param data Le tableau de bytes à décompresser.
     * @return La chaîne de caractères décompressée.
     */
    String decompress(byte[] data);
}
