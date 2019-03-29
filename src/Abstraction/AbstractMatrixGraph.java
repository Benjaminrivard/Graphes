package Abstraction;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Abstract matrix graph.
 *
 * @param <A> the type parameter
 */
public abstract class AbstractMatrixGraph<A extends AbstractNode> implements IGraph {

	//--------------------------------------------------
    // 				Class variables
    //--------------------------------------------------

    /**
     * The Order.
     */
    protected  int order;		// Number of vertices
    /**
     * The M.
     */
    protected  int m=0;			// Number of edges/arcs
    /**
     * The Matrix.
     */
    protected  int[][] matrix;	// The adjacency matrix

    /**
     * The Nodes.
     */
    protected List<AbstractNode> nodes;

    /**
     * Instantiates a new Abstract matrix graph.
     */
//--------------------------------------------------
    // 				Constructors
    //--------------------------------------------------
    public AbstractMatrixGraph() {
        this.matrix = new int[0][0];
        this.order = 0;
        this.m = 0;
    }
    

    // ------------------------------------------
    // 		Accessors
    // ------------------------------------------

    /**
     * Returns the list of nodes in the graph
     *
     * @return the int [ ] [ ]
     */
    public int[][] getMatrix() {
        return this.matrix;
    }

    /**
     * Returns the number of nodes in the graph (referred to as the order of the graph)
     */
    public int getNbNodes() {
        return this.order;
    }



}
