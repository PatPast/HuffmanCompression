public class BinarySearchTreeString extends BinarySearchTree {

    BinarySearchTreeString(Object i){
        super(i);
    }

    public int compareTo(Object i){
        return ((String)this.value).compareTo((String)i);
    }

    public BinarySearchTree newNode(Object i) {
        return new BinarySearchTreeString(i);
    }

    public static void main(String[] args) {
        System.out.println("Test d’arbre binaire de recherche de chaines de caractères");

        System.out.println("\nCréation de l’arbre et ajout de nœuds");
        BinarySearchTreeString a = new BinarySearchTreeString("c");
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
