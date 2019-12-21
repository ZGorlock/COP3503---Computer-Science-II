/**
 * Zachary Gill
 * COP 3503
 * Section number 0001
 * Huffman Compression
 * 21 April 2016
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


/**
 * @author Zachary Gill
 *
 */
public class Huffman
{
    
    public static final String inputFile = "input.dat";
    
    public static byte[] fileData;
    public static int dataLength;
    
    public static int[] freq;
    
    public static BinaryMinHeap<HuffmanTreeNode> queue;
    public static HuffmanTree tree;
    public static CompressionTable table;


    /**
     * @param args
     */
    public static void main(String[] args)
    {
        readInputFile();
        
        calculateFreqs();
                
        createQueue();
        
        tree = HuffmanTree.buildTree(queue);
        table = CompressionTable.buildTable(tree);
        
        //tree.inOrder(tree.getRoot());
                
        makeHuffmanCodes();
    }
    
    
    /**
     * Reads the input file into the file data array.
     */
    private static void readInputFile()
    {
        File input = new File(inputFile);
        fileData = new byte[(int) input.length()];
                
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(input);
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find " + inputFile);
            e.printStackTrace();
            System.exit(0);
        }
        
        dataLength = 0;
        byte in;
        try {
            while ((in = (byte)inputStream.read()) > -1) {
                fileData[dataLength++] = in;
            }
        }
        catch (IOException e) {
            System.out.println("Error reading " + inputFile);
            e.printStackTrace();
        }
    }
    
    /**
     * Calculates the frequency table.
     */
    public static void calculateFreqs()
    {
        freq = new int[256];
        
        for (int i = 0; i < dataLength; i++) {
            freq[fileData[i]]++;
        }
    }
    
    /**
     * Create the minheap queue.
     */
    public static void createQueue()
    {
        queue = new BinaryMinHeap<HuffmanTreeNode>(dataLength);
        
        ArrayList<HuffmanTreeNode> nodes = new ArrayList<HuffmanTreeNode>(); 
        for (int i = 0; i < dataLength; i++) {
            HuffmanTreeNode node = new HuffmanTreeNode(freq[fileData[i]], fileData[i]);
            if (!nodes.contains(node))
                nodes.add(node);
        }
        Collections.sort(nodes);
        
        for (int i = 0; i < nodes.size(); i++)
            queue.add(nodes.get(i));
    }
    
    /**
     * Prints out the Huffman codes.
     */
    public static void makeHuffmanCodes()
    {
        int compressedSize = 0;
        int uncompressedSize = (dataLength * 8);
        
        for (int i = 0; i < dataLength; i++) {
            System.out.println("'" + (char)fileData[i] + "'" + " : " + table.getBitRepresentation(fileData[i]));
            compressedSize += table.getBitRepresentation(fileData[i]).length();
        }
        
        System.out.println();
        System.out.println("Compressed Size: " + compressedSize + " bits");
        System.out.println("Uncompressed Size: " + uncompressedSize + " bits");
        System.out.println("Compression Ratio: " + ((double) compressedSize / uncompressedSize) * 100 + " %");
    }

}
