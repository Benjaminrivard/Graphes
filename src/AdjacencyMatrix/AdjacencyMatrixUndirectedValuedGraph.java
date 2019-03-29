package AdjacencyMatrix;

import java.util.ArrayList;
import java.util.List;

import Abstraction.AbstractNode;
import Abstraction.IUndirectedGraph;
import GraphAlgorithms.GraphTools;
import Nodes.DirectedNode;
import Nodes.UndirectedNode;

/**
 * The type Adjacency matrix undirected valued graph.
 */
public class AdjacencyMatrixUndirectedValuedGraph extends AdjacencyMatrixUndirectedGraph {

	//--------------------------------------------------
	// 				Class variables
	//-------------------------------------------------- 

	private  int[][] matrixCosts;	// The graph with Costs


	//--------------------------------------------------
	// 				Constructors
	//-------------------------------------------------- 

	/**
	 * Instantiates a new Adjacency matrix undirected valued graph.
	 *
	 * @param mat       the mat
	 * @param matrixVal the matrix val
	 */
	public AdjacencyMatrixUndirectedValuedGraph(int[][] mat, int[][] matrixVal) {
		super();
		this.order = mat.length;
		this.matrix = new int[this.order][this.order];
		this.matrixCosts = new int[this.order][this.order];
		for(int i =0;i<this.order;i++){
			for(int j=i;j<this.order;j++){
				int val = mat[i][j];
				int cost = matrixVal[i][j]; 
				this.matrix[i][j] = val;
				this.matrix[j][i] = val;
				this.matrixCosts[i][j] = cost;
				this.matrixCosts[j][i] = cost; 
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
     * removes the edge (x,y) if there exists at least one between these nodes in the graph. And if there remains no arc, removes the cost.
     */
	@Override
	public void removeEdge(AbstractNode x, AbstractNode y) {
		super.removeEdge(x,y);
		if(!super.getNeighbors(x).contains(y.getLabel()) & !super.getNeighbors(y).contains(x.getLabel())){
			this.getMatrixCosts()[x.getLabel()][y.getLabel()] = 0;
			this.getMatrixCosts()[y.getLabel()][x.getLabel()] = 0;
		}
	}

	/**
	 * adds the edge (x,y,cost), we allow the multi-graph. If there is already one initial cost, we keep it.
	 *
	 * @param x    the x
	 * @param y    the y
	 * @param cost the cost
	 */
	public void addEdge(AbstractNode x, AbstractNode y, int cost ) {
		super.addEdge(x,y);
		if(!super.getNeighbors(x).contains(y.getLabel()) & !super.getNeighbors(y).contains(x.getLabel()))
		{
			this.matrixCosts[x.label][y.label] = cost;
			this.matrixCosts[y.label][x.label] = cost;
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
		int[][] matrix = GraphTools.generateGraphData(10, 20, true, true, false, 100001);
        int[][] matrixValued = GraphTools.generateValuedGraphData(10, false, true, true, false, 100001);
		GraphTools.AfficherMatrix(matrix);
		AdjacencyMatrixUndirectedValuedGraph am = new AdjacencyMatrixUndirectedValuedGraph(matrix, matrixValued);
		System.out.println(am);
		am.addEdge(new UndirectedNode(2), new UndirectedNode(5), 13);
		am.addEdge(new UndirectedNode(2), new UndirectedNode(7), 4);
		System.out.println(am);
        System.out.println(am.isEdge(new UndirectedNode(2), new UndirectedNode(5)));
        am.removeEdge(new UndirectedNode(2), new UndirectedNode(5));
        System.out.println(am);
	}

}
