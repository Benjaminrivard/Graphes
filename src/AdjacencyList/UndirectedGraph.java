package AdjacencyList;

import java.util.*;
import java.util.stream.Collectors;

import Abstraction.AbstractListGraph;
import GraphAlgorithms.GraphTools;
import Nodes.UndirectedNode;
import Abstraction.IUndirectedGraph;


public class UndirectedGraph<A extends UndirectedNode> extends AbstractListGraph<A> implements IUndirectedGraph<A> {


    //--------------------------------------------------
    // 				Constructors
    //--------------------------------------------------
	public UndirectedGraph() {
		 this.nodes = new ArrayList<>();
	}
	
	public UndirectedGraph(List<A> nodes) {
        super(nodes);
        for (UndirectedNode i : nodes) {
            this.m += i.getNbNeigh();
        }
    }

    public UndirectedGraph(int[][] matrix) {
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
                    addEdge(this.nodes.get(i), nodes.get(j));
                }
            }
        }
    }

    public UndirectedGraph(UndirectedGraph<A> g) {
        this(g.toAdjacencyMatrix());

    }

    // ------------------------------------------
    // 				Accessors
    // ------------------------------------------
    @Override
    public int getNbEdges() {
        return this.m;
    }    

    @Override
    public List<A> getNeighbors(A x) {
        return this.getNodes().get(x.getLabel()).getNeighbors().stream().map(n -> (A)n).collect(Collectors.toList());
    }
    
    //--------------------------------------------------
    // 					Methods
    //--------------------------------------------------
    
    @Override
    public boolean isEdge(A x, A y) {
    	return getNodeOfList(x).getNeighbors().contains(getNodeOfList(y));
    }

    @Override
    public void removeEdge(A x, A y) {
        getNodeOfList(x).getNeighbors().remove(getNodeOfList(y));
        getNodeOfList(y).getNeighbors().remove(getNodeOfList(x));
        this.m -=1;
    }

    @Override
    public void addEdge(A x, A y) {
        getNodeOfList(x).getNeighbors().add(getNodeOfList(y));
        getNodeOfList(y).getNeighbors().add(getNodeOfList(x));
        this.m +=1;
    }

    /**
     * @return the corresponding nodes in the list this.nodes
     */
    public A getNodeOfList(A src) {
        return this.getNodes().get(src.getLabel());
    }
    
    /**
     * Method to generify node creation
     * @param label of a node
     * @return a node typed by A extends UndirectedNode
     */
    @Override
    public A makeNode(int label) {
        return (A) new UndirectedNode(label);
    }

    /**
     * @return the adjacency matrix representation int[][] of the graph
     */
    @Override
    public int[][] toAdjacencyMatrix() {
        int[][] tab = new int[this.order][this.order];

        for(int i = 0 ; i < this.getNbNodes(); i++)
        {
            for(UndirectedNode a : this.nodes.get(i).getNeighbors()){
                tab[nodes.get(i).label][a.label] +=1 ;
            }
        }
        return tab;
    }

    
    @Override
    public String toString() {
        String s = "";
        for (UndirectedNode n : nodes) {
            s += "neighbors of " + n + " : ";
            for (UndirectedNode sn : n.getNeighbors()) {
                s += sn + " ";
            }
            s += "\n";
        }
        s += "\n";
        return s;
    }

    public List<UndirectedNode> explorerBFSGraph()
    {
        boolean mark[] = new boolean[this.getNbNodes()];

        List<UndirectedNode> toReturn = new ArrayList<>();

        ArrayDeque<UndirectedNode> toVisit = new ArrayDeque<>();

        toVisit.addFirst( this.getNodes().get(0));

        while(!toVisit.isEmpty()){
            UndirectedNode v = ( toVisit).removeLast();
            toReturn.add(v);
            mark[v.getLabel()] = true;
            int  neighbors =  this.getNeighbors((A) v).size();
            for(int i = 0; i < neighbors ; i++) {
                UndirectedNode neighbor = (this.getNeighbors((A) v).get(i));
                if(!mark[neighbor.getLabel()]) {
                    mark[neighbor.getLabel()] = true;
                    (toVisit).addFirst(neighbor);
                }
            }
        }

        return toReturn;
    }



    public static void main(String[] args) {
        int[][] mat = GraphTools.generateGraphData(10, 20, false, true, false, 100001);
        GraphTools.AfficherMatrix(mat);
        UndirectedGraph al = new UndirectedGraph(mat);
        System.out.println(al);
        al.addEdge(new UndirectedNode(2), new UndirectedNode(5));
        System.out.println(al);
        System.out.println(al.isEdge(new UndirectedNode(2), new UndirectedNode(5)));
        al.removeEdge(new UndirectedNode(2), new UndirectedNode(5));
        System.out.println(al);
    }

}
