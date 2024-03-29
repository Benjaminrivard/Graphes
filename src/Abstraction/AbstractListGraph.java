package Abstraction;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Abstract list graph.
 *
 * @param <A> the type parameter
 */
public abstract class AbstractListGraph<A extends AbstractNode> implements IGraph {

    /**
     * The Nodes.
     */
//--------------------------------------------------
    // 				Class variables
    //--------------------------------------------------
	protected List<A> nodes;
    /**
     * The Order.
     */
    protected int order;
    /**
     * The M.
     */
    protected int m;

    /**
     * Instantiates a new Abstract list graph.
     */
//--------------------------------------------------
    // 				Constructors
    //--------------------------------------------------
    public AbstractListGraph() {
        this.nodes = new ArrayList<>();
        this.order = 0;
        this.m = 0;
    }

    /**
     * Instantiates a new Abstract list graph.
     *
     * @param nodes the nodes
     */
    public AbstractListGraph(List<A> nodes) {
        this.nodes = nodes;
        this.order = nodes.size();
        this.m = 0;
    }

    // ------------------------------------------
    // 		Accessors
    // ------------------------------------------
    /**
     * Returns the list of nodes in the graph
     */
    @Override
    public List<A> getNodes() {
        return nodes;
    }

    /**
     * Returns the number of nodes in the graph (referred to as the order of the graph)
     */
    public int getNbNodes() {
        return this.order;
    }

    /**
     * Method to generify node creation
     *
     * @param label of a node
     * @return a node typed by A
     */
    public abstract A makeNode(int label);

}
