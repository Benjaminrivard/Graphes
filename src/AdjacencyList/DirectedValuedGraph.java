package AdjacencyList;

import java.util.ArrayList;

import GraphAlgorithms.GraphTools;
import Nodes.DirectedNode;

/**
 * The type Directed valued graph.
 */
public class DirectedValuedGraph extends DirectedGraph<DirectedNode> {

    /**
     * Instantiates a new Directed valued graph.
     *
     * @param matrixVal the matrix val
     *
     *
     */
//--------------------------------------------------
    // 				Constructors
    //--------------------------------------------------
	public DirectedValuedGraph(int[][] matrixVal) {
        this.nodes = new ArrayList<>();
        for(int i=0; i<matrixVal[0].length;i++){
            nodes.add(makeNode(i));
        }
        for(int i=0; i<nodes.size();i++){
            for(int j=0;j<nodes.size();j++){
                if(matrixVal[i][j] != 0){
                    nodes.get(i).getSuccs().add(nodes.get(j));
                    nodes.get(i).addCosts(matrixVal[i][j]);
                    nodes.get(j).getPreds().add(nodes.get(i));
                    nodes.get(j).addCostsInv(matrixVal[i][j]);
                }
            }
        }

        this.order = nodes.size();
        this.m = 0;
    }



    // ------------------------------------------
    // 				Accessors
    // ------------------------------------------
    
	/**
     * Removes the arc (from,to) with cost if it is present in the graph
     */
    @Override
    public void removeArc(DirectedNode from, DirectedNode to) {
        super.removeArc(from,to);
        getNodeOfList(from).getCosts().remove(getNodeOfList(to));
        getNodeOfList(to).getCostsInv().remove(getNodeOfList(from));

    }


    /**
     * Adds the arc (from,to) with cost  if it is not already present in the graph
     *
     * @param from the from
     * @param to   the to
     * @param cost the cost
     */
    public void addArc(DirectedNode from, DirectedNode to, int cost) {
        super.addArc(from,to);
        getNodeOfList(from).getCosts().add(cost);
        getNodeOfList(to).getCostsInv().add(cost);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int[][] matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);
        int[][] matrixValued = GraphTools.generateValuedGraphData(10, false, false, true, false, 100001);
        GraphTools.AfficherMatrix(matrix);
        GraphTools.AfficherMatrix(matrixValued);
        DirectedValuedGraph al = new DirectedValuedGraph(matrixValued);
        System.out.println(al);
        //DirectedGraph alInv = (DirectedGraph)al.computeInverse();
        //System.out.println(alInv);
        System.out.println(al.getNodes().get(2).getCosts());
        al.addArc(new DirectedNode(2), new DirectedNode(5), 13);
        al.addArc(new DirectedNode(2), new DirectedNode(7), 4);
        System.out.println(al);
        System.out.println(al.getNodes().get(2).getCosts());
        System.out.println(al.isArc(new DirectedNode(2), new DirectedNode(5)));
        al.removeArc(new DirectedNode(2), new DirectedNode(5));
        System.out.println(al.getNodes().get(2).getCosts());
    }
	
}
