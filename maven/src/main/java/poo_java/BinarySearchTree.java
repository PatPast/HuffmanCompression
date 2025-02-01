package poo_java;



/**
 * Classe représentant un arbre binaire de recherche.
 */
public class BinarySearchTree<T extends Comparable<T>> 
    extends BinaryTree<T>  {

    /**
     * Constructeur pour initialiser l'arbre binaire de recherche avec une valeur.
     *
     * @param i la valeur à assigner au nœud racine
     */
    public BinarySearchTree(T i){
        super(i);
    }
    
    /**
     * Ajoute un nœud à l'arbre binaire de recherche. Le nœud est ajouté
     * à la position correcte pour maintenir l'ordre.
     *
     * @param i la valeur à ajouter à l'arbre
     */
    @Override
    public void addNode(T i) {
        if (this.value.compareTo(i) == 0)
            return;
        if (this.value.compareTo(i) > 0){
            if (this.left == null) {
                this.left = new BinarySearchTree<T>(i);
            }
            else this.left.addNode(i);
        }
        else{
            if (this.right == null) {
                this.right = new BinarySearchTree<T>(i);
            }
            else this.right.addNode(i);
        }
    }

    /**
     * Trouve la valeur maximale de l'arbre binaire de recherche.
     * 
     * @return la valeur maximale trouvée dans l'arbre
     */
    protected T findMax() {
        if (this.right == null)
            return this.value;
        return ((BinarySearchTree<T>)this.right).findMax();
    }

    /**
     * Supprime un nœud de l'arbre binaire de recherche.
     * Si le nœud à supprimer a deux enfants, il est remplacé par le maximum du sous-arbre gauche.
     *
     * @param i la valeur du nœud à supprimer
     */
    @Override
    public void removeNode(T i) {
        if (this.value.compareTo(i) > 0 && this.left != null) {
            if (this.left.getValue().compareTo(i) == 0) {
                if (this.left.isLeaf())
                    this.left = null;
                else if (this.left.left == null) {
                    this.left = this.left.right;
                    this.left.right = null;
                }
                else if (this.left.right == null) {
                    this.left = this.left.left;
                    this.left.left = null;
                }
                else {
                    T max = ((BinarySearchTree<T>)this.left).findMax();
                    this.left.value = max;
                    this.left.removeNode(i);
                }
            }
            else
                this.left.removeNode(i);
        }
        else if (this.value.compareTo(i) < 0 && this.right != null) {
            if (this.right.getValue().compareTo(i) == 0) {
                if (this.right.isLeaf())
                    this.right = null;
                else if (this.right.left == null) {
                    this.right = this.right.right;
                    this.right.right = null;
                }
                else if (this.right.right == null) {
                    this.right = this.right.left;
                    this.right.left = null;
                }
                else {
                    T max = ((BinarySearchTree<T>)this.right).findMax();
                    this.right.value = max;
                    this.right.removeNode(i);
                }
            }
            else
                this.right.removeNode(i);
        }
    }

    /**
     * Vérifie si une valeur existe dans l'arbre binaire de recherche.
     *
     * @param i la valeur à vérifier
     * @return true si la valeur existe dans l'arbre, false sinon
     */
    @Override
    public boolean exists(T i) {
        if (this.value.compareTo(i) == 0)
            return true;
        boolean is_found = false;
        if (this.value.compareTo(i) > 0 && this.left != null)
            is_found = is_found || this.left.exists(i);
        if (this.value.compareTo(i) < 0 && this.right != null)
            is_found = is_found || this.right.exists(i);
        return is_found;
    }


    public static void main(String[] args) {
        System.out.println("Test d’arbre binaire de recherche de chaines de caractères");

        System.out.println("\nCréation de l’arbre et ajout de nœuds");
        BinarySearchTree<String> a = new BinarySearchTree<String>("c");
        a.addNode("b");
        a.addNode("a");
        a.addNode("a");
        a.addNode("aa");
        a.addNode("bb");
        a.addNode("d");
        a.addNode("d");
        a.addNode("e");
        a.addNode("e");
        a.addNode("e");

        System.out.println("\nAffichage");
        System.out.println(a);

        System.out.println("\nTest d’existence de nœuds");
        String[] tests_existence = "acek".split("");
        for(String c : tests_existence){
            System.out.println(c + " dans l’arbre ? " + a.exists(c));
        }

        System.out.println("\nTest du calcul de hauteur");
        System.out.println(a.getHeight());

        String[] tests_suppression = "eeeazc".split("");
        for (String c : tests_suppression) {
            System.out.println("\nTest de suppression d’un nœud " + c);
            a.removeNode(c);
            System.out.println(a);
        }

        System.out.println("Test d’arbre binaire de recherche d’entiers");

        System.out.println("\nCréation de l’arbre et ajout de nœuds");
        BinarySearchTree<Integer> b = new BinarySearchTree<Integer>(5);
        b.addNode(4);
        b.addNode(6);
        b.addNode(2);
        b.addNode(12);
        b.addNode(22);
        b.addNode(22);
        b.addNode(22);

        System.out.println("\nAffichage");
        System.out.println(b);

        System.out.println("\nTest d’existence de nœuds");
        for(int i = 0; i < 8; i++){
            System.out.println(i + " dans l’arbre ? " + b.exists(i));
        }

        System.out.println("\nTest du calcul de hauteur");
        System.out.println(b.getHeight());
        
        Integer[] tests_suppression_int = {22,22,2,32,6,4};
        for (int i : tests_suppression_int) {
            System.out.println("\nTest de suppression d’un nœud " + i);
            b.removeNode(i);
            System.out.println(b);
        }
    }
}
