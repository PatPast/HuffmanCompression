package poo_java;

/**
 * Classe représentant un nœud dans un arbre de Huffman.
 */
public class HuffmanNode extends BinaryTree<HuffmanTuple> {
    /**
     * Constructeur pour initialiser un nœud avec un HuffmanTuple.
     * 
     * @param tuple
     */
    public HuffmanNode(HuffmanTuple tuple){
        super(tuple);
    }

    /**
     * Constructeur pour initialiser un nœud avec un sous-arbre gauche et un sous-arbre droit.
     * Ce nœud aura une valeur qui sera la somme des fréquences des sous-arbres.
     * 
     * @param left le sous-arbre gauche
     * @param right le sous-arbre droit
     */
    public HuffmanNode(HuffmanNode left, HuffmanNode right){
        super(new HuffmanTuple(null, left.getValue().getFrequence() + right.getValue().getFrequence()));
        this.left = left;
        this.right = right;
    }
    
}
