import java.util.Random;

public abstract class BinarySearchTree extends BinaryTree {

    BinarySearchTree(Object i){
        super(i);
    }

    @Override
    public void addNode(Object i) {
        if (this.compareTo(i) > 0){
            if (this.left == null) {
                this.left = new BinaryTree(i);
                // System.err.println(i + " fils g de " + this.value.toString());
            }
            else this.left.addNode(i);
        }
        else{
            if (this.right == null) {
                this.right = new BinaryTree(i);
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
        if (this.compareTo(i) > 0 && this.left != null)
            return this.left.exists(i);
        if (this.compareTo(i) < 0 && this.right != null)
            return this.right.exists(i);
        return false;
    }

    public abstract int compareTo(Object i);


    
}