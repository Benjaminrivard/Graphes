package Abstraction;

import java.util.ArrayList;
import java.util.List;

public interface IDirectedGraph<A extends AbstractNode> extends IGraph {

	/**
	 * @return the number of arcs in the graph
 	 */
	int getNbArcs();

	/**
	 * @return true i arc (from,to) gures in the graph
 	 */
	boolean isArc(A from, A to);

	/**
	 * Removes the arc (from,to), if it exists
 	 */
	void removeArc(A from, A to);

	/**
	 * Adds the arc (from,to) if it is not already present in the graph, requires the existing of nodes from and to 
 	 */
	void addArc(A from, A to);

	/**
	 * @return a List of nodes representing the successors of node x
 	 */
	List<A> getSuccessors(A x);

	/**
	 * @return a List of nodes representing the predecessors of node x
 	 */
	List<A> getPredecessors(A x);

	/**
	 * @return a new graph implementing IDirectedGraph interface which is the inverse graph of this
 	 */
	IDirectedGraph computeInverse();

	@Override
	default List<?> getNeighbors(Object x) {
		List<A> list = new ArrayList<>();
		list.addAll(getSuccessors((A) x));
		return list;
	}
}
