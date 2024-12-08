import java.util.Random;

public abstract class BinarySearchTree extends BinaryTree {

    BinarySearchTree(Object i){
        super(i);
    }

    @Override
    public void addNode(Object i) {
        if (this.compareTo(i) > 0){
            if (this.left == null) {
                this.left = newNode(i);
                // System.err.println(i + " fils g de " + this.value.toString());
            }
            else this.left.addNode(i);
        }
        else{
            if (this.right == null) {
                this.right = newNode(i);
                // System.err.println(i + " fils d de " + this.value.toString());
            }
            else this.right.addNode(i);
        }
    }

    @Override
    public void removeNode(Object i) {
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
    public boolean exists(Object i) {
        if (this.compareTo(i) == 0)
            return true;
        boolean is_found = false;
        if (this.compareTo(i) > 0 && this.left != null)
            is_found = is_found || this.left.exists(i);
        if (this.compareTo(i) < 0 && this.right != null)
            is_found = is_found || this.right.exists(i);
        return is_found;
    }

    public abstract int compareTo(Object i);
    public abstract BinarySearchTree newNode(Object i);

}
