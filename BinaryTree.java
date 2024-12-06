import java.util.Random;

public class BinaryTree implements Tree{
    protected String value;
    protected BinaryTree left, right;
    BinaryTree(Object i){
        this.value = i;
        //this.height = 0;
    }

    @Override
    public void addNode(Object i) {
        int whichSide = new Random().nextInt(2);

        if (whichSide == 0){
            if (this.left == null) {
                this.left = new BinaryTree(i);
                System.err.println(i + " fils g de " + this.value.toString());
            }
            else this.left.addNode(i);
        }
        else{
            if (this.right == null) {
                this.right = new BinaryTree(i);
                System.err.println(i + " fils d de " + this.value.toString());
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
                    System.out.println("REMOVELEFT");
                    this.left = null;
                    
                }
            }
            else this.left.removeNode(i); 
        }
        if (this.right != null){
            if (this.right.isLeaf()){
                if (this.right.getValue() == i){ 
                    System.out.println("REMOVERIGHT");
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
        //res += "\n";
        return res;
    }

    public static void main(String[] args) {
        BinaryTree a = new BinaryTree(5);
        a.addNode(4);
        a.addNode(6);
        a.addNode(2);
        a.addNode(12);
        a.addNode(22);
        a.addNode(22);
        a.addNode(22);

        // System.err.println(a);
        // System.out.println(a.display(1));
        System.out.println(a);
        for(int i = 0; i < 8; i++){
            System.out.println(a.exists(i));
        }
        System.out.println(a.getHeight());

        
        a.removeNode(22);
        System.out.println(a);
        a.removeNode(22);
        System.out.println(a);
        a.removeNode(22);
        System.out.println(a);

    }
    
}
