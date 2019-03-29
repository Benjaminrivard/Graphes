package Nodes;

import java.util.ArrayList;
import java.util.List;

import Abstraction.AbstractNode;


public class DirectedNode extends AbstractNode {

    //--------------------------------------------------
    // 				Class variables
    //--------------------------------------------------
    List<DirectedNode> succs;
    List<DirectedNode> preds;
    List<Integer> arcCosts; 	// Costs linked to succs
    List<Integer> arcInvCosts;  // Costs linked to preds

    //--------------------------------------------------
    // 				Constructors
    //--------------------------------------------------
    public DirectedNode(int i) {
        super(i);
        this.succs = new ArrayList<>();
        this.preds = new ArrayList<>();
        this.arcCosts = new ArrayList<>();
        this.arcInvCosts = new ArrayList<>();
    }
    
    public DirectedNode(int i, int p) {
        super(i,p);
        this.succs = new ArrayList<>();
        this.preds = new ArrayList<>();
        this.arcCosts = new ArrayList<>();
        this.arcInvCosts = new ArrayList<>();
    }

    // ------------------------------------------
    // 		Accessors
    // ------------------------------------------

    /**
     * @return the list of successors of the current node this
     */
    public List<DirectedNode> getSuccs() {
        return this.succs;
    }

    /**
     * @return the list of predecessors of the current node this
     */
    public List<DirectedNode> getPreds() {
        return preds;
    }

    /**
     * add a cost of value cost
     */
    public void addCosts(int val){
        this.arcCosts.add(val);
    }

    /**
     * @return the list of costs associated with the successors of the current node
     */
    public List<Integer> getCosts() {
        return arcCosts;
    }
    
    /**
     * add a cost of value cost to costsInv
     */
    public void addCostsInv(int val){
        this.arcInvCosts.add(val);
    }

    /**
     * @return the list of costs associated with the predecessors of the current node
     */
    public List<Integer> getCostsInv() {
        return arcInvCosts;
    }

    /**
     * @return the number of successors of node this
     */
    public int getNbSuccs() {
        return succs.size();
    }

    /**
     * @return the number of predecessors of node this
     */
    public int getNbPreds() {
        return preds.size();
    }

    /**
     * @param succs the new list of successors for node this
     */
    public void setSuccs(List<DirectedNode> succs) {
        this.succs = succs;
    }

    /**
     * @param preds the new list of predecessors for nodes this
     */
    public void setPreds(List<DirectedNode> preds) {
        this.preds = preds;
    }

    /**
     * @param costs the new list of costs associated with the successors of node this
     */
	public void setCosts(List<Integer> costs) {
		this.arcCosts = costs;
	}
	
	/**
     * @param costs the new list of costs associated with the successors of node this
     */
	public void setCostsInv(List<Integer> costs) {
		this.arcInvCosts = costs;
	}

    
    
}
