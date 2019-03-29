package Abstraction;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract node.
 */
public abstract class AbstractNode {

	/**
	 * The Label.
	 */
//--------------------------------------------------
	// 				Class variables
	//-------------------------------------------------- 
	public int label;
	/**
	 * The Weight.
	 */
	public int weight;

	/**
	 * Instantiates a new Abstract node.
	 *
	 * @param i the
	 */
//--------------------------------------------------
	// 				Constructors
	//-------------------------------------------------- 	
	public AbstractNode(int i) {
		this.label = i;
		this.weight = 0;
	}

	/**
	 * Instantiates a new Abstract node.
	 *
	 * @param i the
	 * @param p the p
	 */
	public AbstractNode(int i, int p) {
		this(i);
		this.weight = p;
	}
	
	// ------------------------------------------
	// 
	// 		Accessors
	//
	// ------------------------------------------

	/**
	 * Get label int.
	 *
	 * @return an integer which is a unique identifier of a node
	 */
	public int getLabel(){
		return this.label;
	}

	/**
	 * Get weight int.
	 *
	 * @return an integer which is a weight associated with a node
	 */
	public int getWeight(){
			return this.weight;
	}

	/**
	 * setter for the weight variable
	 *
	 * @param p the p
	 */
	public void setWeight(int p){
		this.weight = p;
	}

	/**
	 * check if two nodes are equals => the label is the key
	 * @param n an object which is an abstract node
	 * @return true iff this and n are equal
     */
    public boolean equals(Object n) {
		return n == this || ( n!=null &&
							  n instanceof AbstractNode && 
							  ((AbstractNode) n).getLabel() == this.getLabel());
	}

	
	public String toString() {
		String s = "node-"+label;
		if(weight>0)
			s+= "-"+weight;
		return s;
	}
}
