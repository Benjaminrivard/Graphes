package AdjacencyList;

import java.util.*;
import java.util.stream.Collectors;

import Abstraction.AbstractListGraph;
import GraphAlgorithms.GraphTools;
import Nodes.DirectedNode;
import Abstraction.IDirectedGraph;


/**
 * The type Directed graph.
 *
 * @param <A> the type parameter
 */
public class DirectedGraph<A extends DirectedNode> extends AbstractListGraph<A> implements IDirectedGraph<A> {

	//private static int _DEBBUG =1;
	private static int _DEBBUG =0;


    /**
     * Instantiates a new Directed graph.
     */
//--------------------------------------------------
    // 				Constructors
    //--------------------------------------------------
	public DirectedGraph(){
		super();
		this.nodes = new ArrayList<>();
	}

    /**
     * Instantiates a new Directed graph.
     *
     * @param matrix the matrix
     */
    public DirectedGraph(int[][] matrix) {
        super();

        this.order = matrix.length;
        //on remplie la liste des noeuds
        for(int k= 0; k < order; k++){
            this.nodes.add(makeNode(k));
        }

        for(int i =0 ; i < order; i++)
        {
            for(int j = 0; j <order; j++){
                for(int k = 0; k < matrix[i][j]; k++) {
                    //comme on peut avoir affaire a des multis graphes
                    addArc(this.nodes.get(i), nodes.get(j));
                }
            }
        }

    }

    /**
     * Instantiates a new Directed graph.
     *
     * @param g the g
     */
    public DirectedGraph(DirectedGraph<A> g) {

        this(g.toAdjacencyMatrix());
    }

    // ------------------------------------------
    // 		Accessors
    // ------------------------------------------
    @Override
    public int getNbArcs() {
        return this.m;
    }

    @Override
    public List<A> getSuccessors(A x) {
        return this.getNodes().get(x.getLabel()).getSuccs().stream().map(n -> (A)n).collect(Collectors.toList());
    }

    @Override
    public List<A> getPredecessors(A x) {
        return this.getNodes().get(x.getLabel()).getPreds().stream().map(n -> (A)n).collect(Collectors.toList());
    }

    //--------------------------------------------------
    // 				Methods
    //--------------------------------------------------
    
    @Override
    public boolean isArc(A from, A to) {
    	return getNodeOfList(from).getSuccs().contains(getNodeOfList(to));
    }

    @Override
    public void removeArc(A from, A to) {
        getNodeOfList(from).getSuccs().remove(getNodeOfList(to));
        getNodeOfList(to).getPreds().remove(getNodeOfList(from));
        this.m -=1;
    }

    @Override
    public void addArc(A from, A to) {
        getNodeOfList(from).getSuccs().add(getNodeOfList(to));
        getNodeOfList(to).getPreds().add(getNodeOfList(from));
        this.m += 1;
    }
    
    /**
     * Method to generify node creation
     * @param label of a node
     * @return a node typed by A extends DirectedNode
     */
    @Override
    public A makeNode(int label) {
        return (A)new DirectedNode(label);
    }

    /**
     * Gets node of list.
     *
     * @param src the src
     * @return the corresponding nodes in the list this.nodes
     */
    public A getNodeOfList(A src) {
        return this.getNodes().get(src.getLabel());
    }

    /**
     * @return the adjacency matrix representation int[][] of the graph
     */
    @Override
    public int[][] toAdjacencyMatrix() {
        int[][] tab = new int[this.order][this.order];

        for(int i = 0 ; i < this.getNbNodes(); i++)
        {
            for(DirectedNode a : this.nodes.get(i).getSuccs()){
                tab[nodes.get(i).label][a.label] +=1 ;
            }
        }
        return tab;
    }

    @Override
    public IDirectedGraph computeInverse() {
        DirectedGraph<A> inverse = new DirectedGraph<>(this);
        for(A node : inverse.getNodes()) {
            List<DirectedNode> succ = node.getSuccs();
            node.setSuccs(node.getPreds());
            node.setPreds(succ);
        }
        return inverse;
    }
    
 	
    @Override
    public String toString(){
        String s = "";
        for(DirectedNode n : nodes){
            s+="successors of "+n+" : ";
            for(DirectedNode sn : n.getSuccs()){
                s+=sn+" ";
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
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        System.out.println("=========== matrice ===========");
        GraphTools.AfficherMatrix(Matrix);
        DirectedGraph al = new DirectedGraph(Matrix);
        System.out.println("=========== graph ===========");
        System.out.println(al);
        DirectedGraph alInv = (DirectedGraph)al.computeInverse();
        System.out.println("=========== inverse ===========");
        System.out.println(alInv);
        al.addArc(new DirectedNode(2), new DirectedNode(5));
        System.out.println("=========== add arc ===========");
        System.out.println(al);
        System.out.println("=========== is arc ===========");
        System.out.println(al.isArc(new DirectedNode(2), new DirectedNode(5)));
        System.out.println("=========== remove ===========");
        al.removeArc(new DirectedNode(2), new DirectedNode(5));
        System.out.println(al);
    }
}
