package poo_java;


import java.nio.file.*;
import java.io.IOException;

/**
 * Classe servant à gérer les entrées/sorties avec des fichiers.
 */
public class FileManager {
    /**
     * Lit un fichier.
     *
     * @param filePath Chemin du fichier
     * @return Le contenu du fichier sous forme de chaine de caractères
     */
    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    /**
     * Lit un fichier en mode binaire.
     *
     * @param filePath Chemin du fichier
     * @return Le contenu du fichier sous forme de tableau d'octets
     */
    public static byte[] readFileAsBytes(String filePath) throws IOException {
        return Files.readAllBytes(Paths.get(filePath));
    }

    /**
     * Écrit dans un fichier en mode binaire.
     *
     * @param filePath Chemin du fichier
     * @param data tableau d'octets des données à écrire
     */
    public static void writeFile(String filePath, byte[] data) throws IOException {
        Files.write(Paths.get(filePath), data);
    }
}
