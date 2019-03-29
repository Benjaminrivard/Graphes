package AdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;

import Abstraction.AbstractNode;
import Abstraction.IDirectedGraph;
import GraphAlgorithms.GraphTools;
import Nodes.DirectedNode;

/**
 * The type Adjacency matrix directed valued graph.
 */
public class AdjacencyMatrixDirectedValuedGraph extends AdjacencyMatrixDirectedGraph {

	//--------------------------------------------------
	// 				Class variables
	//-------------------------------------------------- 

	private  int[][] matrixCosts;	// The graph with Costs


	//--------------------------------------------------
	// 				Constructors
	//-------------------------------------------------- 

	/**
	 * Instantiates a new Adjacency matrix directed valued graph.
	 *
	 * @param mat       the mat
	 * @param matrixVal the matrix val
	 */
	public AdjacencyMatrixDirectedValuedGraph(int[][] mat, int[][] matrixVal) {
		super();
		this.order = mat.length;
		this.matrix = new int[this.order][this.order];
		this.matrixCosts = new int[this.order][this.order];
		for(int i =0;i<this.order;i++){
			for(int j=0;j<this.order;j++){
				int val = mat[i][j];
				int cost = matrixVal[i][j]; 
				this.matrix[i][j] = val;				
				this.matrixCosts[i][j] = cost; 
				this.m += val;					
			}
		}
	}


	//--------------------------------------------------
	// 					Accessors
	//--------------------------------------------------

	/**
	 * Get matrix costs int [ ] [ ].
	 *
	 * @return the matrix with costs of the graph
	 */
	public int[][] getMatrixCosts() {
		return matrixCosts;
	}

	
	// ------------------------------------------------
	// 					Methods
	// ------------------------------------------------	
	
	/**
     * removes the arc (from,to) if there exists at least one between these nodes in the graph. And if there remains no arc, removes the cost.
     */
	@Override
	public void removeArc(AbstractNode from, AbstractNode to) {
		super.removeArc(from,to);
		if(!super.getSuccessors(from).contains(to.getLabel())) {
			this.matrixCosts[from.label][to.label] = 0;
		}
	}

	/**
	 * adds the arc (from,to,cost), we allow the multi-graph. If there is already one initial cost, we keep it.
	 *
	 * @param from the from
	 * @param to   the to
	 * @param cost the cost
	 */
	public void addArc(AbstractNode from, AbstractNode to, int cost ) {
		super.addArc(from,to);
		if(!super.getSuccessors(from).contains(to.getLabel()))
		{
			this.matrixCosts[from.label][to.label] = cost;
		}
	}
	
	public String toString() {
		String s = super.toString()+"\n Matrix of Costs: \n";
		for(int i =0;i<this.matrixCosts.length;i++){
			for(int j =0;j<this.matrixCosts[i].length;j++){
				s+=this.matrixCosts[i][j]+" ";
			}
			s+="\n";
		}
		s+="\n";
		return s;
	}


	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		int[][] matrix = GraphTools.generateGraphData(10, 30, false, false, false, 100001);
        int[][] matrixValued = GraphTools.generateValuedGraphData(10, false, false, true, false, 100001);
		AdjacencyMatrixDirectedValuedGraph am = new AdjacencyMatrixDirectedValuedGraph(matrix, matrixValued);
		System.out.println(am);

		System.out.println("======== is arc  [2][5]");
		System.out.println(am.isArc(new DirectedNode(2), new DirectedNode(5)));
		System.out.println("======== ADD ARC [2][5] cost = 13");
		am.addArc(new DirectedNode(2), new DirectedNode(5), 13);
		System.out.println("======== ADD ARC [2][7] cost = 7");
		am.addArc(new DirectedNode(2), new DirectedNode(7), 4);
		System.out.println(am);
		System.out.println("======== is arc  [2][5]");
        System.out.println(am.isArc(new DirectedNode(2), new DirectedNode(5)));
		System.out.println("======== REMOVE ARC [2][5] cost = 13");
        am.removeArc(new DirectedNode(2), new DirectedNode(5));
        System.out.println(am);
		System.out.println("======== is arc  [2][5]");
		System.out.println(am.isArc(new DirectedNode(2), new DirectedNode(5)));
	}

}
