package poo_java;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class HuffmanNode extends BinaryTree implements Comparable<HuffmanNode> {

    public HuffmanNode(Object i){
        super(i);
    }

    @Override
    public int compareTo(HuffmanNode other){
        Object myValue = ((ImmutablePair)this.value).getValue();
        Object otherValue = ((ImmutablePair)other.value).getValue();
        return ((Comparable)myValue).compareTo((Comparable)otherValue);
    }
}
