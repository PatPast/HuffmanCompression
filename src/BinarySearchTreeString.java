public class BinarySearchTreeString extends BinarySearchTree {

    BinarySearchTreeString(Object i){
        super(i);
    }

    public int compareTo(Object i){
        return ((String)this.value).compareTo((String)i);
    }

    public static void main(String[] args) {
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

        // System.err.println(a);
        // System.out.println(a.display(1));
        String[] test = "abcde".split("");
        System.out.println(a);
        for(String c : test){
            System.out.print(a.exists(c));
            System.out.println(" " + c);
        }
        System.out.println(a.getHeight());

        
        a.removeNode("e");
        System.out.println(a);
        a.removeNode("e");
        System.out.println(a);
        a.removeNode("e");
        System.out.println(a);
    }
    
}
