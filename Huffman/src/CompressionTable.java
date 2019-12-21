/**
 * Zachary Gill
 * COP 3503
 * Section number 0001
 * Huffman Compression
 * 21 April 2016
 */

import java.util.ArrayList;
import java.util.List;


/**
 * @author Zachary Gill
 *
 */
public class CompressionTable
{
    
    private List<Byte> bytes;
    private List<String> bits;
    

    /**
     * Creates a new compression table
     */
    public CompressionTable()
    {
        bytes = new ArrayList<Byte>();
        bits = new ArrayList<String>();
    }
    
    /**
     * Adds a new conversion to the compression table.
     * 
     * @param b: The byte.
     * @param s: The bit representation.
     */
    public void add(byte b, String s)
    {
        if (!existsInTable(b)) {
            bytes.add(b);
            bits.add(s);
        }
    }
    
    /**
     * Gets the bit representation of a certain byte.
     * 
     * @param b: The byte to represent.
     * @return The bit representation as a string.
     */
    public String getBitRepresentation(byte b)
    {
        if (!existsInTable(b))
            return "?";
        
        return bits.get(bytes.indexOf(b));
    }
    
    /**
     * Tests is a byte already exists in the compression table.
     * 
     * @param b: The byte to check for.
     * @return Whether it is already in the table or not.
     */
    public boolean existsInTable(byte b)
    {
        return (bytes.contains(new Byte(b)));
    }
    
    /**
     * Creates a compression table from a huffman tree.
     * 
     * @param tree: The huffman tree to use.
     * @return The compression table.
     */
    public static CompressionTable buildTable(HuffmanTree tree)
    {
        CompressionTable table = new CompressionTable();
        
        buildBitRepresentation(table, tree.getRoot(), "");
        
        return table;
    }
    
    /**
     * Recursviely traverses a huffman tree and builds bit representations.
     * 
     * @param table: The table to store the bit representations.
     * @param node: The current node in the huffman tree.
     * @param prefix: The previous bits from traversing the tree.
     */
    public static void buildBitRepresentation(CompressionTable table, HuffmanTreeNode node, String prefix)
    {
        if (node == null)
            return;
        
        if (node.getValue() > -1) {
            table.add((byte) node.getValue(), prefix);
            return;
        }
        
        buildBitRepresentation(table, node.getLeft(), new String(prefix + "1"));
        buildBitRepresentation(table, node.getRight(), new String(prefix + "0"));
    }

}
