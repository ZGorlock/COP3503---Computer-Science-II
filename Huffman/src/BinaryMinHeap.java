/**
 * Zachary Gill
 * COP 3503
 * Section number 0001
 * Huffman Compression
 * 21 April 2016
 */

import java.util.Arrays;


/**
 * @author Zachary Gill
 *
 */
public class BinaryMinHeap <T extends Comparable<T>>
{

    private T[] queue;
    private int size;
    
    /**
     * Creates a new BinaryMinHeap.
     */
    @SuppressWarnings("unchecked")
    public BinaryMinHeap(int maxSize)
    {
        queue = (T[]) new Comparable[maxSize];
        this.size = 0;
    }
    
    /**
     * Adds a new element to the heap.
     */
    public void add(T element)
    {
        if (size >= queue.length - 1) {
            queue = resize();
        }

        queue[size] = element;
        siftUp(size);
        size++;
        
        //printHeap();
    }
    
    /**
     * Pops the smallest element off the heap.
     * 
     * @return The smallest element in the heap.
     */
    public T pop()
    {
        if (size() == 0)
            return null;
        
        T ret = queue[0];
        
        queue[0] = queue[size - 1];
        size--;

        //printHeap();
        if (size() > 0)
            siftDown(0);
        
        
        return ret;
    }
    
    /**
     * Returns the size of the heap.
     * 
     * @return The size of the heap.
     */
    public int size()
    {
        return size;
    }
    
    /**
     * Returns the index of the left child.
     * 
     * @param index: The index of the node.
     * @return The index of the left child of that node.
     */
    private int getLeftChild(int index)
    {
        return index * 2 + 1;
    }

    /**
     * Returns the index of the right child.
     * 
     * @param index: The index of the node.
     * @return The index of the right child of that node.
     */
    private int getRightChild(int index)
    {
        return index * 2 + 2;
    }

    /**
     * Returns the index of the parent.
     * 
     * @param index: The index of the node.
     * @return The index of the parent of that node.
     */
    private int getParent(int index)
    {
        return (index - 1) / 2;
    }
    
    /**
     * Fixes the elements of a min heap.
     * 
     * @param index: The index to fix.
     */
    private void siftUp(int index)
    {
        if (index == 0)
            return;
        
        int parent = getParent(index);
        
        if (queue[parent].compareTo(queue[index]) > 0) { //if a > b
            swap(parent, index);
            siftUp(parent);
        }
    }
    
    /**
     * Fixes the elements of a min heap.
     */
    private void siftDown()
    {
        siftDown(0);
        
        //printHeap();
    }
    
    /**
     * Fixes the elements of a min heap.
     */
    private void siftDown(int index)
    {
        int leftChild = getLeftChild(index);
        int rightChild = getRightChild(index);
        int smaller = 0;
        
        if (leftChild < size) {
            if (rightChild < size) {
                if (queue[leftChild].compareTo(queue[rightChild]) <= 0)
                   smaller = leftChild;
                else
                    smaller = rightChild;
            }
            else {
                smaller = leftChild;
            }
        }
        else if (rightChild < size) {
            smaller = rightChild;
        }
        else {
            return;
        }
        
        if (queue[index].compareTo(queue[smaller]) > 0) {
            swap(smaller, index);

            //printHeap();
            siftDown(smaller);
        }
    }
    
    /**
     * Resizes the queue.
     *  
     * @return The new queue.
     */
    private T[] resize()
    {
        return Arrays.copyOf(queue, queue.length * 2);
    }
    
    /**
     * Swaps two elements in the queue.
     * 
     * @param index1: The first index to swap.
     * @param index2: The second index to swap.
     */
    private void swap(int index1, int index2)
    {
        T tmp = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = tmp;
    }
    
    /**
     * Checks if a node is already in the heap.
     * 
     * @param node: The node to look for.
     * @return Whether it exists in the heap or not.
     */
    public boolean exists(T node)
    {
        for (int i = 0; i < size; i++) {
            if (queue[i].equals(node))
                return true;
        }
        return false;
    }
    
    /**
     * Prints out the binary heap.
     */
    public void printHeap()
    {
        printHeap(0, 0);
        System.out.println();
    }
    
    /**
     * Prints out the binary heap.
     * 
     * @param index: The current index of the node you are looking at.
     * @param level: The level of the node.
     */
    private void printHeap(int index, int level)
    {
        if (index >= size)
            return;
        for (int i = 0; i < level; i++) {
            System.out.print("----");
        }
        System.out.println(queue[index]);
        printHeap(getLeftChild(index), level + 1);
        printHeap(getRightChild(index), level + 1);
    }
    
    /**
     * Prints out the array of data in the heap.
     */
    public void printQueue()
    {
        for (int i = 0; i < queue.length; i++) {
            if (queue[i]!= null )
                System.out.print(queue[i] + " ");
        }
        System.out.println();
    }
    
}
