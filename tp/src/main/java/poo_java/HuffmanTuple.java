package poo_java;

/**
 * Classe représentant un tuple de Huffman.
 * Un tuple de Huffman est composé d'un caractère et d'une fréquence.
 */
public class HuffmanTuple implements Comparable<HuffmanTuple> {
    private Character caractere;
    private Integer frequence;

    /**
     * Constructeur pour initialiser un tuple de Huffman.
     * 
     * @param caractere le caractère
     * @param frequence la fréquence
     */
    public HuffmanTuple(Character caractere, Integer frequence){
        this.caractere = caractere;
        this.frequence = frequence;
    }
    
    /**
     * Retourne le caractère associé à ce tuple de Huffman.
     *
     * @return le caractère associé à ce tuple de Huffman
     */
    public Character getCaractere(){
        return this.caractere;
    }

    /**
     * Retourne la fréquence associée à ce tuple de Huffman.
     *
     * @return la fréquence associée à ce tuple de Huffman
     */
    public Integer getFrequence(){
        return this.frequence;
    }

    /**
     * Compare ce tuple de Huffman à un autre tuple de Huffman.
     * La comparaison se fait sur la fréquence.
     *
     * @param other le tuple de Huffman à comparer
     * @return un entier négatif si ce tuple a une fréquence plus petite que l'autre,
     *         un entier positif si ce tuple a une fréquence plus grande que l'autre,
     *         0 si les fréquences sont égales
     */
    @Override
    public int compareTo(HuffmanTuple other){
        return this.frequence - other.getFrequence();
    }

    /**
     * Retourne une représentation textuelle de ce tuple de Huffman.
     * La représentation est de la forme (caractère : fréquence), sauve si le caractère est null.
     * Dans ce cas, la représentation est de la forme (fréquence).
     *
     * @return une représentation textuelle de ce tuple de Huffman
     */
    public String toString(){
        String carString = "";
        if (this.caractere != null)
            carString = this.caractere.toString() + " : ";
        return "(" + carString + this.frequence + ")";
    }
}
