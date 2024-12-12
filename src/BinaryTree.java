import java.util.Random;

public class BinaryTree implements Tree{
    protected Object value;
    protected BinaryTree left, right;
    BinaryTree(Object i){
        this.value = i;
    }

    @Override
    public void addNode(Object i) {
        int whichSide = new Random().nextInt(2);

        if (whichSide == 0){
            if (this.left == null) {
                this.left = new BinaryTree(i);
            }
            else this.left.addNode(i);
        }
        else{
            if (this.right == null) {
                this.right = new BinaryTree(i);
            }
            else this.right.addNode(i);
        }
    }

    public Object getValue(){
        return this.value;
    }

    @Override
    public void removeNode(Object i) {
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

    protected boolean isLeaf(){
        return this.left == null && this.right == null;
    }

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

    @Override
    public int getHeight() {
        return getHeightRecursive(0);
    }

    @Override
    public boolean exists(Object i) {
        if (this.value == i)
            return true;
        if (this.left != null && this.left.exists(i))
            return true;
        if (this.right != null && this.right.exists(i))
            return true;
        return false;
    }

    public String toString(){
        return this.display(1);
    }

    public String display(int depth){
        String res = this.value + "\n";
        if (this.left != null) res += "\t".repeat(depth) + "l " + this.left.display(depth+1);
        if (this.right != null) res += "\t".repeat(depth) + "r " + this.right.display(depth+1);
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Test d’arbre binaire");

        System.out.println("\nCréation de l’arbre et ajout de nœuds");
        BinaryTree a = new BinaryTree(5);
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
        
        Integer[] tests_suppression = {22,22,5,32,6};
        for (int i : tests_suppression) {
            System.out.println("\nTest de suppression d’un nœud " + i);
            a.removeNode(i);
            System.out.println(a);
        }
    }
}
