/**
 * Zachary Gill
 * COP 3503
 * Section number 0001
 * Recitation 5
 * 11 February 2016
 */

public class BST 
{
	// This is the root node, which starts off as null
	//   when the BST is empty.
	BSTNode m_objRootNode;
	
	/**
	 * Holds the k value to test during the k-time test
	 */
	int kValue;
	
    // Class constructor.
	public BST()
	{
		// Not really necessary, provided for clarity.
		m_objRootNode = null;
		kValue = 3;
	}

	// Method to see if the tree is empty.
	public boolean IsEmpty()
	{
		// Return a boolean indicating whether the
		//   three is empty or not.
		return( m_objRootNode == null );
	}

	/* Functions to search for an element */
    public BSTNode Search( int nKeyValue )
    {
        return( Search( m_objRootNode, nKeyValue ) );
    }
    
    // Method to search for an element recursively.
    private BSTNode Search( BSTNode objNode, int nKeyValue )
    {
    	
    	if( objNode == null )
    	{
    		return( null );
    	}
    	
    	// First, we get the key value for this node.
    	int nThisKeyValue = objNode.GetKeyValue();
            
    	// See if the passed in key value is less. If so,
    	//   this indicates that we need to go left.
    	if( nKeyValue < nThisKeyValue )
    	{
    		// Get the left node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetLeftNode();
    	}
            
    	// See if the passed in key value is greater. If so,
    	//   this indicates that we need to go right.
    	else if( nKeyValue > nThisKeyValue )
    	{
    		// Get the right node object reference so we
    		//   can walk down it.
    		objNode = objNode.GetRightNode();
    	}

    	// Here we have found the node with the key
    	//   value that was passed in.
    	else
    	{
    		return( objNode );
    	}
            
    	// Now call Search recursively.
    	return( Search( objNode, nKeyValue ) );
	}
    
    // This method inserts a node based on the key value.
    public void Insert( int nKeyValue ) 
    {
    	// The root node is returned to m_objRootNode from Insert()
    	m_objRootNode = Insert( nKeyValue, m_objRootNode );
    }    

    // Class protected (internal) method to insert nodes. This method
    //   will be called recursively.
    protected BSTNode Insert( int nKeyValue, BSTNode objNode ) 
    {
    	// This node is null and simply needs to be allocated.
        if( objNode == null )
        {
        	objNode = new BSTNode( nKeyValue );
        	return objNode;
        }
        
        if (!objNode.PassesKTimeTest(nKeyValue, kValue))
            return objNode;
        
        // Here we need to walk left.
        if( nKeyValue < objNode.GetKeyValue() )
        {
        	// Set the left node of this object by recursively walking left.
        	objNode.SetLeftNode( Insert( nKeyValue, objNode.GetLeftNode() ) ); //move the new node ot the left subtree
        	//objNode.subtree_size++; //incremement the subtree size counter
        	objNode.left.updateSubtreeSize(); //update the subtree sizes
        	objNode.GetLeftNode().SetParent(objNode); //set the parent node of the left subtree
        }
        
        // Here we need to walk right.
        else if( nKeyValue > objNode.GetKeyValue() )
        {
        	// Set the right node of this object by recursively walking right.
        	objNode.SetRightNode( Insert( nKeyValue, objNode.GetRightNode() ) ); //move the new node to the right subtree
            objNode.right.updateSubtreeSize(); //update the subtree sizes
            //objNode.subtree_size++; //increment the subtree size counter
            objNode.GetRightNode().SetParent(objNode); //set the parent node of the right subtree
        }
        
        return( objNode );
    }
    
    /**
     * Deletes a node in a BST by nValue
     * @param nValue : The value to delete
     * @return Whether the delete was successful
     */
    public boolean Delete(int nValue)
    {
        return Delete(Search(nValue));
    }
    
    /**
     * Deletes a node in a BST by reference
     * @param ds : The reference to the node to delete
     * @return Whether the delete was successful
     */
    private boolean Delete(BSTNode ds)
    {
        if (ds == null) //node not found
            return false;
                
        return ds.Delete(); //attempt to delete the node
    }
    
    /**
     * Returns the minimum node in the BST
     * @return The minimum node value
     */
    public int getMin()
    {
        return m_objRootNode.getMin(); //get the min in the entire tree
    }
    
    /**
     * Returns the maximum node in the BST
     * @return The maximum node value
     */
    public int getMax()
    {
        return m_objRootNode.getMax(); //get the max in the entire tree
    }

    /**
     * Returns the minimum node in the BST
     * @return The minimum node
     */
    public BSTNode getMinNode()
    {
        return m_objRootNode.getMinNode(); //get the min in the entire tree
    }
    
    /**
     * Returns the maximum node in the BST
     * @return The maximum node
     */
    public BSTNode getMaxNode()
    {
        return m_objRootNode.getMaxNode(); //get the max in the entire tree
    }
    
    /**
     * Returns the rank of the node specified.
     * @param nValue : The value of the node to look for
     * @return The rank of this node
     */
    public int getRank(int nValue)
    {
        return getRank(Search(nValue), m_objRootNode);
    }

    /**
     * Returns the rank of the node specified.
     * @param ds : The node
     * @return The rank of this node
     */
    public int getRank(BSTNode ds)
    {
        return getRank(ds, m_objRootNode);
    }
    
    /**
     * Returns the rank of the node specified.
     * @param ds : The node
     * @param objNode : The node to start from
     * @return The rank of this node
     */
    public int getRank(BSTNode ds, BSTNode objNode)
    {
        if (ds == null || objNode == null)
            return 0;
        
        int tmpRank = 1;
        
        if (ds.GetKeyValue() > objNode.GetKeyValue()) { //if ds is to the right
            tmpRank += getRank(ds, objNode.right); //add left subtree and root and move right
            if (objNode.left != null)
                tmpRank += objNode.left.subtree_size;
        }
        else if (ds.GetKeyValue() < objNode.GetKeyValue()) { //if ds is to the left
            return getRank(ds, objNode.left); //move left
        }
        else { //found the node
            if (objNode.left != null)
                tmpRank += objNode.left.subtree_size;
        }
        return tmpRank;
    }
    
    /**
     * Returns the root node
     * @return The root node
     */
    public BSTNode getRootNode()
    {
        return m_objRootNode;
    }

    /**
     * @return the kValue
     */
    public int getkValue()
    {
        return this.kValue;
    }

    /**
     * @param kValue the kValue to set
     */
    public void setkValue(int kValue)
    {
        this.kValue = kValue;
    }
    
    /**
     * Traverses the tree in order
     */
    public void traverseInOrder()
    {
        traverseInOrder(m_objRootNode);
        System.out.println();
    }
    
    /**
     * 
     * Traverses the tree in order
     * @param obj : The root node
     */
    public void traverseInOrder(BSTNode obj)
    {
        if (obj == null)
            return;
        
        traverseInOrder(obj.left);
        System.out.print(obj.value + " ");
        traverseInOrder(obj.right);
    }
}
