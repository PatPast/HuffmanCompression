package poo_java;
import java.util.Random;

/**
 * Classe représentant un arbre binaire.
 */
public class BinaryTree<T extends Comparable<T>> 
    implements Tree<T>, Comparable<BinaryTree<T>>{
    /**
     * La valeur du nœud actuel.
     */
    protected T value;
    /**
     * Le sous-arbre gauche.
     */
    protected BinaryTree<T> left;
    /**
     * Le sous-arbre right.
     */
    protected BinaryTree<T> right;
    /**
     * Générateur de nombres aléatoires pour déterminer de quel côté ajouter un nœud.
     */
    private static final Random RANDOM = new Random();
    /**
     * Constructeur pour initialiser l'arbre binaire avec une valeur.
     *
     * @param i la valeur à assigner au nœud racine
     */
    public BinaryTree(T i){
        this.value = i;
    }

    /**
     * Définit le sous-arbre gauche de ce nœud de l'arbre binaire.
     *
     * @param node le nœud à définir comme sous-arbre gauche
     */
    public void setLeft(BinaryTree<T> node) {
        this.left = node;
    }

    /**
     * Définit le sous-arbre droit de ce nœud de l'arbre binaire.
     *
     * @param node le nœud à définir comme sous-arbre droit
     */
    public void setRight(BinaryTree<T> node) {
        this.right = node;
    }

    /**
     * Retourne le sous-arbre gauche de ce nœud de l'arbre binaire.
     *
     * @return le sous-arbre gauche de ce nœud de l'arbre binaire
     */
    public BinaryTree<T> getLeft() {
        return this.left;
    }

    /**
     * Retourne le sous-arbre droit de ce nœud de l'arbre binaire.
     *
     * @return le sous-arbre droit de ce nœud de l'arbre binaire
     */
    public BinaryTree<T> getRight() {
        return this.right;
    }

    /**
     * Ajoute un nœud à l'arbre binaire. Le nœud est ajouté aléatoirement
     * au sous-arbre gauche ou droit.
     *
     * @param i la valeur à ajouter à l'arbre
     */
    @Override
    public void addNode(T i) {
        int whichSide = RANDOM.nextInt(2);

        if (whichSide == 0){
            if (this.left == null) {
                this.left = new BinaryTree<T>(i);
            }
            else this.left.addNode(i);
        }
        else{
            if (this.right == null) {
                this.right = new BinaryTree<T>(i);
            }
            else this.right.addNode(i);
        }
    }

    /**
     * Retourne la valeur du nœud actuel.
     *
     * @return la valeur du nœud actuel
     */
    public T getValue(){
        return this.value;
    }

    /**
     * Supprime un nœud de l'arbre binaire.
     * La suppression ne marche que pour les feuilles.
     *
     * @param i la valeur du nœud à supprimer
     */
    @Override
    public void removeNode(T i) {
        if (this.left != null){
            if (this.left.isLeaf()){
                if (this.left.getValue() == i){ 
                    this.left = null;
                }
            }
            else this.left.removeNode(i); 
        }
        if (this.right != null){
            if (this.right.isLeaf()){
                if (this.right.getValue() == i){ 
                    this.right = null;
                }
            }
            else this.right.removeNode(i); 
        }
    }

    /**
     * Vérifie si le nœud actuel est une feuille (n'a pas de sous-arbres).
     *
     * @return true si le nœud est une feuille, false sinon
     */
    protected boolean isLeaf(){
        return this.left == null && this.right == null;
    }

    /**
     * Calcule la hauteur de l'arbre de manière récursive.
     *
     * @param h la hauteur actuelle
     * @return la hauteur totale de l'arbre
     */
    protected int getHeightRecursive(int h) {
        if (this.isLeaf())
            return h + 1;
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.left != null)
            leftHeight = this.left.getHeightRecursive(h);
        if (this.right != null)
            rightHeight = this.right.getHeightRecursive(h);
        if (leftHeight > rightHeight)
            return h + 1 + leftHeight;
        else
            return h + 1 + rightHeight;
    }

    /**
     * Retourne la hauteur de l'arbre.
     *
     * @return la hauteur de l'arbre
     */
    @Override
    public int getHeight() {
        return getHeightRecursive(0);
    }

    /**
     * Vérifie si une valeur existe dans l'arbre.
     *
     * @param i la valeur à vérifier
     * @return true si la valeur existe dans l'arbre, false sinon
     */
    @Override
    public boolean exists(T i) {
        if (this.value == i)
            return true;
        if (this.left != null && this.left.exists(i))
            return true;
        if (this.right != null && this.right.exists(i))
            return true;
        return false;
    }

    /**
     * Vérifie si une valeur existe dans l'arbre.
     *
     * @param i la valeur à vérifier
     * @return true si la valeur existe dans l'arbre, false sinon
     */
    public String toString(){
        return this.display(1);
    }

    /**
     * Affiche l'arbre de manière récursive.
     *
     * @param level le niveau actuel de l'arbre
     * @return une chaîne de caractères représentant l'arbre
     */
    public String display(int depth){
        String res = this.value + "\n";
        if (this.left != null) res += "\t".repeat(depth) + "l " + this.left.display(depth+1);
        if (this.right != null) res += "\t".repeat(depth) + "r " + this.right.display(depth+1);
        return res;
    }

    /**
     * Compare cet arbre binaire à un autre arbre binaire.
     * La comparaison se fait sur la valeur du nœud.
     *
     * @param i l'arbre binaire à comparer
     * @return un entier négatif si la valeur de ce nœud est plus petite que l'autre,
     *         un entier positif si la valeur de ce nœud est plus grande que l'autre,
     *         0 si les valeurs sont égales
     */
    @Override
    public int compareTo(BinaryTree<T> i) {
        return this.value.compareTo(i.getValue());
    }

    public static void main(String[] args) {
        System.out.println("Test d’arbre binaire");

        System.out.println("\nCréation de l’arbre et ajout de nœuds");
        BinaryTree<Integer> a = new BinaryTree<Integer>(5);
        a.addNode(4);
        a.addNode(6);
        a.addNode(2);
        a.addNode(12);
        a.addNode(22);
        a.addNode(22);
        a.addNode(22);

        System.out.println("\nAffichage");
        System.out.println(a);

        System.out.println("\nTest d’existence de nœuds");
        for(int i = 0; i < 8; i++){
            System.out.println(i + " dans l’arbre ? " + a.exists(i));
        }

        System.out.println("\nTest du calcul de hauteur");
        System.out.println(a.getHeight());
        
        Integer[] tests_suppression = {22,22,2,32,6};
        for (int i : tests_suppression) {
            System.out.println("\nTest de suppression d’un nœud " + i);
            a.removeNode(i);
            System.out.println(a);
        }
    }
}
