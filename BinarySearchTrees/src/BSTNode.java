/**
 * Zachary Gill
 * COP 3503
 * Section number 0001
 * Recitation 5
 * 11 February 2016
 */

public class BSTNode 
{
	BSTNode left, right;
	BSTNode parent;
	int value;
	int subtree_size;
	
    public BSTNode()
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		left = right = null;
		parent = null;
		
		// Set this node's key value to default of 0.
		value = 0;
        
		//Set this node's subtree size to only itself
		subtree_size = 1;
	}

    public BSTNode(int nKeyValue)
	{
		// Explicit set to null may not be necessary,
		//   but provided for clarity.
		left = right = null;
        parent = null;
		
		// Set this node's key value
		value = nKeyValue;
		
        //Set this node's subtree size to only itself
        subtree_size = 1;
	}
    
    /**
     * Deletes a node
     * @return Whether the operation was successful of not
     */
    public boolean Delete()
    {
        if (left != null && right != null) { //both child nodes
            BSTNode tmpNode = right.getMinNode();
            value = tmpNode.value; //get the leftmost child of the right child
            return tmpNode.Delete();
        }
        else {
            subtree_size--; //decremenent subtree size before delete
            if (parent != null)
                parent.updateSubtreeSize(); //update subtreesizes before delete
            
            if (left != null) { //only left child
                if (isLeftNode()) //if it is the left child of its parent
                    parent.SetLeftNode(left);
                else if (isRightNode()) //if it is the right child of its parent
                    parent.SetRightNode(left);
                left.SetParent(parent); //set parent of child node to parent of deleted node
            }
            else if (right != null) { //only right child
                if (isLeftNode()) //if it is the left child of its parent
                    parent.SetLeftNode(right);
                else if (isRightNode()) //if it is the right child of its parent
                    parent.SetRightNode(right);
                right.SetParent(parent); //set parent of child node to parent of deleted node
            }
            else { //no child nodes
                if (isLeftNode())
                    parent.SetLeftNode(null);
                else if (isRightNode())
                    parent.SetRightNode(null);
            }
        }
        
        return true; //successful
    }
    
    /**
     * Updates the subtree size of a node and all its parents
     */
    public void updateSubtreeSize()
    {
        subtree_size = 1;
        
        if (left != null)
            subtree_size += left.subtree_size; //add left subtree size
        if (right != null)
            subtree_size += right.subtree_size; //add right subtree size
        
        if (parent != null)
            parent.updateSubtreeSize(); //move up the tree towards the root
    }
    
    /**
     * Returns the number of children the node has
     * @return The number of children
     */
    public int NumChildren()
    {
        int children = 0;
        if (GetLeftNode() != null)
            children++;
        if (GetRightNode() != null)
            children++;
        return children;
    }
    
    /**
     * Tests if a node is a left child node to its parent
     * @return Whether it is a left child node of not
     */
    public boolean isLeftNode()
    {
        BSTNode parent = GetParent();
        if (parent == null)
            return false;
        
       if (parent.GetLeftNode() == this)
           return true;
       return false;
    }

    /**
     * Tests if a node is a right child node to its parent
     * @return Whether it is a right child node of not
     */
    public boolean isRightNode()
    {
        BSTNode parent = GetParent();
        if (parent == null)
            return false;
        
       if (parent.GetRightNode() == this)
           return true;
       return false;
    }
    
    /**
     * Returns the minimum node in a node tree
     * @return The minimum node value
     */
    protected int getMin()
    {
        BSTNode node = this.getMinNode(); //get the node
        if (node == null)
            return 0;
        return node.GetKeyValue();
    }

    /**
     * Returns the maximum node in a node tree
     * @return The maximum node value
     */
    protected int getMax()
    {
        BSTNode node = this.getMaxNode(); //get the node
        if (node == null)
            return 0;
        return node.GetKeyValue();
    }

    /**
     * Returns the minimum node in a node tree
     * @return The minimum node
     */
    protected BSTNode getMinNode()
    {
        if (left == null) //base case, the tree doesn't go any further left
            return this;
        
        return left.getMinNode(); //else, move left in the tree
    }

    /**
     * Returns the maximum node in a node tree
     * @return The maximum node
     */
    protected BSTNode getMaxNode()
    {
        if (right == null) //base case, the tree doesn't go any further right
            return this;
        
        return right.getMaxNode(); //else, move right in the tree
    }
    
	// Accessor method to set the left node.
	public void SetLeftNode( BSTNode objLeftNode)
	{
		// Assign the left node object reference.
		left = objLeftNode;
	}
	
	// Accessor method to set the right node.
	public void SetRightNode( BSTNode objRightNode)
	{
		// Assign the right node object reference.
		right = objRightNode;
	}
	
	// Accessor method to get the left node object.
	public BSTNode GetLeftNode()
	{
		// Return the object.
		return( left );
	}
	
	// Accessor method to get the right node object.
	public BSTNode GetRightNode()
	{
		// Return the object.
		return( right );
	}

    /**
     * @return the m_parent
     */
    public BSTNode GetParent()
    {
        return this.parent;
    }

    /**
     * @param m_parent the m_parent to set
     */
    public void SetParent(BSTNode parent)
    {
        this.parent = parent;
    }
    
	// Accessor method to set the node's key value.
	public void SetKeyValue( int nKeyValue )
	{
		// Set the value.
		value = nKeyValue;
	}
	
	// Accessor method to get the node's key value.
	public int GetKeyValue()
	{
		// Return the value.
		return( value );
	}
    
    /**
     * @return the subtree_size
     */
    public int getSubtreeSize()
    {
        return this.subtree_size;
    }
    
    /**
     * Tests if a new node passes the k-time test
     * @param addedValue : The value of the node to be added
     * @param k : The k-value
     * @return Whether it passes the test or not
     */
    public boolean PassesKTimeTest(int addedValue, int k)
    {
        int dif = Math.abs(addedValue - this.value); //get distance between node value and new value
        return (dif >= k);
    }
    
    /**
     * Finds and returns the root node
     * @return The root node
     */
    public BSTNode getRootNode()
    {
        if (parent == null) //no more parents
            return this;
        
        return parent.getRootNode();
    }

}
