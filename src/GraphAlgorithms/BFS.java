package GraphAlgorithms;

import Abstraction.AbstractNode;
import Abstraction.IGraph;
import AdjacencyList.DirectedGraph;
import AdjacencyList.UndirectedGraph;

import java.util.*;

/**
 * Perform a Breadth First Search on a given graph
 */
public class BFS {

    /**
     * The graph that will be evaluated
     */
    private IGraph graph;

    /**
     * Instantiates a new Bfs.
     *
     * @param graph the graph
     */
    public BFS(IGraph graph) {
        this.graph = graph;
    }


    /**
     * Explorer graph list. Imperative method
     *
     * Time complexity : O(n+m)
     *
     * @return the list of explored node, ordered by order of visit.
     */
    public List<AbstractNode> explorerGraph() {

        Set<AbstractNode> mark = new HashSet<>();

        List<AbstractNode> visitedNodes = new ArrayList<>();

        Queue<AbstractNode> queue = new LinkedList<>();
        queue.add((AbstractNode) graph.getNodes().get(0));

        while(graph.getNbNodes() != visitedNodes.size()) {
            while (!queue.isEmpty()) {
                AbstractNode v = queue.remove();
                visitedNodes.add(v);
                mark.add(v);
                for (AbstractNode succ : (List<AbstractNode>) graph.getNeighbors(v)) {
                    if (!mark.contains(succ)) {
                        mark.add(succ);
                        queue.add(succ);
                    }
                }
            }


            for(AbstractNode a : (List<AbstractNode>) graph.getNodes())
            {
                if(!mark.contains(a))
                {
                    queue.add(a);
                    break;
                }
            }
        }


        return visitedNodes;
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);

        DirectedGraph al = new DirectedGraph(Matrix);

        UndirectedGraph au = new UndirectedGraph(Matrix);

        BFS directed = new BFS(al);
        BFS undirected = new BFS(au);

        System.out.println("Graphe dirigé : " + directed.explorerGraph());

        System.out.println("Graphe non dirigé :" + undirected.explorerGraph());

    }

}
