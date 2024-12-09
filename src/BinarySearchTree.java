import java.util.Random;

public class BinarySearchTree extends BinaryTree {

    BinarySearchTree(Object i){
        super(i);
    }

    @Override
    public void addNode(Object j) {
        Comparable i = (Comparable) j;
        if (this.compareTo(i) > 0){
            if (this.left == null) {
                // this.left = newNode(i);
                this.left = new BinarySearchTree(i);
                // System.err.println(i + " fils g de " + this.value.toString());
            }
            else this.left.addNode(i);
        }
        else{
            if (this.right == null) {
                // this.right = newNode(i);
                this.right = new BinarySearchTree(i);
                // System.err.println(i + " fils d de " + this.value.toString());
            }
            else this.right.addNode(i);
        }
    }

    @Override
    public void removeNode(Object j) {
        Comparable i = (Comparable) j;
        if (this.compareTo(i) > 0){
            if (this.left != null ) 
                if (this.left.isLeaf()) this.left = null;
                else this.left.removeNode(i);
        }
        else{
            if (this.right != null ) 
                if (this.right.isLeaf()) this.right = null;
                else this.right.removeNode(i);
        }
    }

    @Override
    public boolean exists(Object j) {
        Comparable i = (Comparable) j;
        if (this.compareTo(i) == 0)
            return true;
        boolean is_found = false;
        if (this.compareTo(i) > 0 && this.left != null)
            is_found = is_found || this.left.exists(i);
        if (this.compareTo(i) < 0 && this.right != null)
            is_found = is_found || this.right.exists(i);
        return is_found;
    }

    public int compareTo(Object j) {
        Comparable i = (Comparable) j;
        return ((Comparable)this.value).compareTo(i);
    }
    // public abstract int compareTo(Object i);
    // public abstract BinarySearchTree newNode(Object i);

    public static void main(String[] args) {
        System.out.println("Test d’arbre binaire de recherche de chaines de caractères");

        System.out.println("\nCréation de l’arbre et ajout de nœuds");
        BinarySearchTree a = new BinarySearchTree("c");
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
    }
}
