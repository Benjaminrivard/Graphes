package AdjacencyList;

import java.util.ArrayList;
import java.util.List;

import GraphAlgorithms.GraphTools;
import Nodes.UndirectedNode;

public class UndirectedValuedGraph extends UndirectedGraph<UndirectedNode> {

	//--------------------------------------------------
    // 				Constructors
    //--------------------------------------------------
    public UndirectedValuedGraph(int[][] matrixVal) {
    	// A completer 	
    }

    //--------------------------------------------------
    // 				Methods
    //--------------------------------------------------
    
    /**
     * Removes the edge (from,to) and its cost if the edge is present in the graph
     */
    @Override
    public void removeEdge(UndirectedNode from, UndirectedNode to) {
    	// A completer
    }

    /**
     * Adds the edge (from,to) with cost if it is not already present in the graph
     */
    public void addEdge(UndirectedNode from, UndirectedNode to, int cost) {
    	// A completer
    }
    
    
    public static void main(String[] args) {
        int[][] matrix = GraphTools.generateGraphData(10, 15, false, true, false, 100001);
        int[][] matrixValued = GraphTools.generateValuedGraphData(10, false, true, true, false, 100001);
        GraphTools.AfficherMatrix(matrix);
        GraphTools.AfficherMatrix(matrixValued);
        UndirectedValuedGraph al = new UndirectedValuedGraph(matrixValued);
        System.out.println(al);
        System.out.println(al.getNodes().get(2).getCosts());
        al.addEdge(new UndirectedNode(2), new UndirectedNode(5), 13);
        al.addEdge(new UndirectedNode(2), new UndirectedNode(7), 4);
        System.out.println(al);
        System.out.println(al.getNodes().get(2).getCosts());
        System.out.println(al.isEdge(new UndirectedNode(2), new UndirectedNode(5)));
        al.removeEdge(new UndirectedNode(2), new UndirectedNode(5));
        System.out.println(al.getNodes().get(2).getCosts());
        System.out.println(al);
    }
	
}
