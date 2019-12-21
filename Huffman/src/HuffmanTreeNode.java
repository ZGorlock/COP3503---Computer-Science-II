/**
 * Zachary Gill
 * COP 3503
 * Section number 0001
 * Huffman Compression
 * 21 April 2016
 */


/**
 * @author Zachary Gill
 *
 */
public class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{

    private int freq;
    private int value;
    
    private HuffmanTreeNode left;
    private HuffmanTreeNode right;
    private HuffmanTreeNode parent;

    /**
     * Creates a new Huffman Tree Node.
     * 
     * @param freq: The frequency value of the node.
     * @param value: The value of the node.
     * @param parent: The parent of the node.
     */
    public HuffmanTreeNode(int freq, int value, HuffmanTreeNode parent)
    {
        this.freq = freq;
        this.value = value;
        this.parent = parent;
    }
    
    /**
     * Creates a new Huffman Tree Node.
     * 
     * @param freq: The frequency value of the node.
     * @param value: The value of the node.
     */
    public HuffmanTreeNode(int freq, int value)
    {
        this(freq, value, null);
    }
    
    /**
     * Accessor for the frequency.
     * 
     * @return The frequency.
     */
    public int getFreq()
    {
        return freq;
    }
    
    /**
     * Accessor for the value.
     * 
     * @return The value.
     */
    public int getValue()
    {
        return value;
    }
    
    /**
     * Accessor for the left child of the node.
     * 
     * @return The left child of the node.
     */
    public HuffmanTreeNode getLeft()
    {
        return left;
    }

    /**
     * Accessor for the right child of the node.
     * 
     * @return The right child of the node.
     */
    public HuffmanTreeNode getRight()
    {
        return right;
    }

    /**
     * Accessor for the parent of the node.
     * 
     * @return The parent of the node.
     */
    public HuffmanTreeNode getParent()
    {
        return parent;
    }
    
    /**
     * @param left the left to set
     */
    public void setLeft(HuffmanTreeNode left)
    {
        this.left = left;
    }
    
    /**
     * @param right the right to set
     */
    public void setRight(HuffmanTreeNode right)
    {
        this.right = right;
    }
    
    /**
     * @param parent the parent to set
     */
    public void setParent(HuffmanTreeNode parent)
    {
        this.parent = parent;
    }
    
    /**
     * Creates a string to represent the data node.
     * 
     * @return A string representing the node.
     */
    @Override
    public String toString()
    {
        return (char)value + ":" + freq;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(HuffmanTreeNode o)
    {
        if (freq > o.getFreq())
            return 1;
        else if (freq < o.getFreq())
            return -1;
        return 0;
    }
    
    /**
     * Tests if two nodes are equal.
     * 
     * @param o: The node to test against.
     * @return Whether the two nodes are equal or not.
     */
    @Override
    public boolean equals(Object o)
    {
        if (freq == ((HuffmanTreeNode)o).getFreq() && value == ((HuffmanTreeNode)o).getValue())
            return true;
        return false;
    }

}
