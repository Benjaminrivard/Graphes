package AdjacencyMatrix;
import Abstraction.AbstractNode;
import Abstraction.AbstractMatrixGraph;
import GraphAlgorithms.GraphTools;
import Nodes.DirectedNode;
import Abstraction.IDirectedGraph;

import java.util.ArrayList;
import java.util.List;


/**
 * This class represents the directed graphs structured by an adjacency matrix.
 * It is possible to have simple and multiple graph
 */
public class AdjacencyMatrixDirectedGraph extends AbstractMatrixGraph<DirectedNode> implements IDirectedGraph {

	//--------------------------------------------------
	// 				Constructors
	//-------------------------------------------------- 

	/**
	 * Instantiates a new Adjacency matrix directed graph.
	 */
	public AdjacencyMatrixDirectedGraph() {
		super();
	}

	/**
	 * Instantiates a new Adjacency matrix directed graph.
	 *
	 * @param M the m
	 */
	public AdjacencyMatrixDirectedGraph(int[][] M) {
		this.order=M.length;
		this.matrix = new int[this.order][this.order];
		for(int i =0;i<this.order;i++){
			for(int j=0;j<this.order;j++){
				int val = M[i][j];
				this.matrix[i][j] = val;
				this.m+=val; 
			}
		}
	}

	/**
	 * Instantiates a new Adjacency matrix directed graph.
	 *
	 * @param g the g
	 */
	public AdjacencyMatrixDirectedGraph(IDirectedGraph<DirectedNode> g) {
		this.order=g.getNbNodes(); 
		this.m=g.getNbArcs(); 
		this.matrix = g.toAdjacencyMatrix();
	}
	
	
	//--------------------------------------------------
	// 					Accessors
	//--------------------------------------------------

	@Override
	public int getNbArcs() {
		return this.m;
	}


	@Override
	public List<Integer> getSuccessors(AbstractNode x) {
		List<Integer> v = new ArrayList<Integer>();
		for(int i =0;i<this.matrix[x.getLabel()].length;i++){
			if(this.matrix[x.getLabel()][i]>0){
				v.add(i);
			}
		}		
		return v;
	}
	

	@Override
	public List<Integer> getPredecessors(AbstractNode x) {
		List<Integer> v = new ArrayList<Integer>();
		for(int i =0;i<this.matrix.length;i++){
			if(this.matrix[i][x.getLabel()]>0){
				v.add(i);
			}
		}	
		return v;
	}
	

	// ------------------------------------------------
	// 					Methods
	// ------------------------------------------------	
	
	@Override
	public boolean isArc(AbstractNode from, AbstractNode to) {
		if(this.matrix[from.getLabel()][to.getLabel()]>0)
			return true;
		return false;
	}

	/**
     * removes the arc (from,to) if there exists at least one between these nodes in the graph.
     */
	@Override
	public void removeArc(AbstractNode from, AbstractNode to) {
		int arc = this.getMatrix()[from.label][to.label];
		if(arc > 1) {
			arc -=1;
		}
	}

	/**
     * Adds the arc (from,to). we allow multiple graph.
     */
	@Override
	public void addArc(AbstractNode from, AbstractNode to) {
		this.getMatrix()[from.label][to.label] += 1;

	}
	
	/**
     * @return the adjacency matrix representation int[][] of the graph
     */
	public int[][] toAdjacencyMatrix() {
		return this.matrix;
	}

	@Override
	public IDirectedGraph<DirectedNode> computeInverse() {
		// A completer
		int[][] tab = new int[order][order];
		for(int i =0;i<this.order;i++){
			for(int j=0;j<this.order;j++){
				int a = this.matrix[i][j];
				tab[i][j] = this.matrix[j][i];
				tab[j][i] = a;
			}
		}
		return new AdjacencyMatrixDirectedGraph(tab);
	}

	@Override
	public String toString(){
		String s = "Adjacency Matrix: \n";
		for(int i =0;i<matrix.length;i++){
			for(int j =0;j<matrix[i].length;j++){
				s += matrix[i][j]+" ";
			}
			s += "\n";
		}
		s += "\n";
		return s;
	}

	/**
	 * Make node directed node.
	 *
	 * @param label the label
	 * @return the directed node
	 */
	public DirectedNode makeNode(int label) {
		return new DirectedNode(label);
	}

	@Override
	public List<?> getNodes()
	{
		if(this.nodes == null){
			this.nodes = new ArrayList<>();
			for(int i=0; i< matrix[0].length; i++){
				this.nodes.add(makeNode(i));
			}
		}
		return this.nodes;
	}


	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		int[][] matrix2 = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
		AdjacencyMatrixDirectedGraph am = new AdjacencyMatrixDirectedGraph(matrix2);
		System.out.println(am);
		List<Integer> t = am.getSuccessors(new DirectedNode(1));
		for(int i =0;i<t.size();i++)
			System.out.print(t.get(i)+", ");
		System.out.println();
		List<Integer> t2 = am.getPredecessors(new DirectedNode(2));
		for(int i =0;i<t2.size();i++)
			System.out.print(t2.get(i)+", ");
		System.out.println("\n");
		IDirectedGraph<DirectedNode> im2 = am.computeInverse();
		System.out.println(im2);
		am.addArc(new DirectedNode(2), new DirectedNode(5));
		System.out.println(am);
	    System.out.println(am.isArc(new DirectedNode(2), new DirectedNode(5)));
	    am.removeArc(new DirectedNode(2), new DirectedNode(5));
	    System.out.println(am);
	}


}
