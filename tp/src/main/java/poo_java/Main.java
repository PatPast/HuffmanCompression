package poo_java;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;

import org.apache.commons.cli.ParseException;


// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    // protected static final Logger logger =LogManager.getLogger();
    public static void main(String[] args) {

        System.out.println("hello");
        // logger.info("Hello World");
        Options options = new Options();
        options.addOption("c", "compress", false, "Compresser un fichier");
        options.addOption("d", "decompress", false, "Décompresser un fichier");
        options.addOption("i", "input", true, "Chemin du fichier d'entrée");
        options.addOption("o", "output", true, "Chemin du fichier de sortie");
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            String inputFile = cmd.getOptionValue("i");
            String outputFile = cmd.getOptionValue("o");
            if (cmd.hasOption("c")) {
                // Compression
                String data = FileManager.readFile(inputFile);
                HuffmanCompression compressor = new HuffmanCompression();
                byte[] compressedData = compressor.compress(data);
                System.exit(0);
                FileManager.writeFile(outputFile, compressedData);
                System.out.println("Fichier compressé : " + outputFile);
            } else if (cmd.hasOption("d")) {
                // Décompression
                byte[] compressedData = FileManager.readFileAsBytes(inputFile);
                HuffmanCompression compressor = new HuffmanCompression();
                String decompressedData = compressor.decompress(compressedData);
                FileManager.writeFile(outputFile, decompressedData.getBytes());
                System.out.println("Fichier décompressé : " + outputFile);
            } else {
                System.out.println("Veuillez spécifier une option : --compress ou --decompress");
            }
        } catch (ParseException | IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}