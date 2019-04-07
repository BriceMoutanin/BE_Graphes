/**
 * 
 */
package org.insa.graph;

/**
 * <p>
 * Class representing a Label associated to a Node.
 * </p>
 * 
 * <p>
 * This class holds information regarding how the nodes
 * in the graph have been marked to find the shortest path.
 * </p>
 *
 */


/**
 * Create a Label for the corresponding node.
 * 
 * @param int current_node ID of the node which the Label is associated to.
 * @param int cost Current cost from the origin to this node.
 * @param Arc father Arc which represents the father of the considered node.
 * 
 * @return The newly created forward Label.
 */

public class Label {

	/**
	 * 
	 * Create a Label for the corresponding node.
	 * @param current_node ID of the node which the Label is associated to.
	 * @param cost Current cost from the origin to this node.
	 * @param father father Arc which represents the father of the considered node.
	 * 
	 * @return The newly created forward Label.
	 */
    public Label(int current_node, int cost, Arc father) {
        this.setCurrent_node(current_node);
        this.setCost(cost);
        this.setFather(father);
        this.setMarque(false);
    }
    
    /**
     * Create a Label for the origin node. (No need of the father)
     * 
     * @param current_node ID of the node which the Label is associated to.
     * @param cost Current cost from the origin to this node.
     * 
     * @return The newly created forward Label for the origin.
     */
    public Label(int current_node, int cost) {
        this.setCurrent_node(current_node);
        this.setCost(cost);
        this.setFather(null);
        this.setMarque(true);
    }
	
    /**
     * ID of the node which the Label is associated to.
     */
    private int current_node;
    
    /**
     * True if the minimum cost of the current_node is definitely known.
     */
    private boolean marque;
    
    /**
     * Current cost from the origin to the current_node.
     */
    private int cost;
    
    /**
     * Arc which represents the father of the current_node.
     */
    private Arc father;
   
    /**
     * @return the ID of the current_node.
     */
	public int getCurrent_node() {
		return current_node;
	}

	/**
	 * 
	 * @param current_node
	 */
	public void setCurrent_node(int current_node) {
		this.current_node = current_node;
	}

	/**
     * @return true if the current_node is marked.
     */
	public boolean isMarque() {
		return marque;
	}

	public void setMarque(boolean marque) {
		this.marque = marque;
	}

	/**
     * @return the cost of the current_node from the origin.
     */
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
     * @return the father.
     */
	public Arc getFather() {
		return father;
	}

	public void setFather(Arc father) {
		this.father = father;
	}

}
