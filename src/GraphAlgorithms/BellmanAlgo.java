package GraphAlgorithms;

import AdjacencyList.DirectedValuedGraph;
import Nodes.DirectedNode;

import java.util.Arrays;

public class BellmanAlgo {

    public static int[] Bellman(DirectedValuedGraph graph, DirectedNode s)
    {
        int[] dist = new int[graph.getNbNodes()];

        for(DirectedNode v : graph.getNodes())
        {
            dist[v.getLabel()] = Integer.MAX_VALUE;
        }

        dist[s.getLabel()] = 0;

        for(int k =0; k < graph.getNbNodes()-1 ; k++)
        {
            for(DirectedNode v :  graph.getNodes())
            {
                int d = dist[v.getLabel()];
                for(DirectedNode u : graph.getPredecessors(v))
                {
                    if(dist[u.getLabel()] + u.getCosts().get(v.getLabel()) > 0) {
                        d = dist[u.getLabel()] + u.getCosts().get(u.getSuccs().indexOf(v));
                    }
                }
                dist[v.getLabel()] = d;
            }
        }

        return dist;
    }

    public static void main(String[] args)
    {
        int[][] matrixValued = GraphTools.generateValuedGraphData(10, false, false, true, false, 100001);
        DirectedValuedGraph al = new DirectedValuedGraph(matrixValued);
        System.out.println(Arrays.toString(Bellman(al,al.getNodes().get(0))));
    }

}
