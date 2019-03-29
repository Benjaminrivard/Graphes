package Nodes;

import java.util.ArrayList;
import java.util.List;

import Abstraction.AbstractNode;

/**
 * Created by xlorca on 02/11/2017.
 */
public class UndirectedNode extends AbstractNode {

    //--------------------------------------------------
    // 				Class variables
    //--------------------------------------------------
    List<UndirectedNode> neighbors;
    List<Integer> costs;

    //--------------------------------------------------
    // 				Constructors
    //--------------------------------------------------
    public UndirectedNode(int i) {
        super(i);
        this.neighbors = new ArrayList<UndirectedNode>();
        this.costs = new ArrayList<>();
    }

    // ------------------------------------------
    // 		Accessors
    // ------------------------------------------
    /**
     * @return the list of neighbors of the current node this
     */
    public List<UndirectedNode> getNeighbors() {
        return neighbors;
    }

    /**
     * @param neighbors the new list of neighbors for node this
     */
	public void setNeighbors(List<UndirectedNode> neighbors) {
		this.neighbors = neighbors;
	}

    /**
     * add a cost of value cost
     */
	public void addCosts(int val){
	       this.costs.add(val);
	}

    /**
     * @return the number of neighbors of node this
     */
	public int getNbNeigh() {
        return neighbors.size();
    }

    /**
     * @return the list of costs associated with the neighbors of the current node
     */
	public List<Integer> getCosts() {
		return costs;
	}

    /**
     * @param costs the new list of costs associated with the successors of node this
     */
	public void setCosts(List<Integer> costs) {
		this.costs = costs;
	}
	
	
}
