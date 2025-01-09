package poo_java;

public class HuffmanNode extends BinaryTree implements Comparable<HuffmanNode> {

    public HuffmanNode(Object i){
        super(i);
    }

    @Override
    public int compareTo(HuffmanNode other){
        return ((Comparable)this.value).compareTo((Comparable)other.value);
    }
}
