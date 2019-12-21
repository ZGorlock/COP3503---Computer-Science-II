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
public class HuffmanTree
{
    
    private HuffmanTreeNode root;

    /**
     * Creates a new HuffmanTree.
     */
    public HuffmanTree()
    {
        root = null;
    }
    
    /**
     * Sets the root node of the tree.
     * 
     * @param root: The new root of the tree.
     */
    public void setRoot(HuffmanTreeNode root)
    {
        this.root = root;
    }
    
    /**
     * Accessor for tree root.
     * 
     * @return The tree root.
     */
    public HuffmanTreeNode getRoot()
    {
        return root;
    }
    
    /**
     * Creates a huffman tree from a min heap queue.
     * 
     * @param queue: The minheap queue.
     * @return The new huffman tree.
     */
    public static HuffmanTree buildTree(BinaryMinHeap<HuffmanTreeNode> queue)
    {
        HuffmanTree tree = new HuffmanTree();

        while (queue.size() > 1) {
            HuffmanTreeNode node1 = queue.pop();
            HuffmanTreeNode node2 = queue.pop();
            
            HuffmanTreeNode parent = new HuffmanTreeNode(node1.getFreq() + node2.getFreq(), -1);
            parent.setLeft(node1);
            parent.setRight(node2);
            node1.setParent(parent);
            node2.setParent(parent);
            
            tree.setRoot(parent);
            
            queue.add(parent);
        }
        
        return tree;
    }
    
    /**
     * Prints out the tree in order.
     * 
     * @param root: The node to start at.
     */
    public static void inOrder(HuffmanTreeNode root)
    {
        inOrderAt(root);
        System.out.println();
    }
    
    /**
     * Prints out the tree in order.
     * 
     * @param root: The node to start at.
     */
    public static void inOrderAt(HuffmanTreeNode root) {  
        if(root !=  null) {  
            inOrderAt(root.getLeft()); 
            System.out.printf("(%d:%s) ", root.getFreq(), (char)root.getValue());  
            inOrderAt(root.getRight());
        }
    }
}
