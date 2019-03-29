package GraphAlgorithms;

import Abstraction.IDirectedGraph;
import AdjacencyList.DirectedGraph;
import Nodes.DirectedNode;

import java.util.*;

/**
 * Utility class that finds strongly connected components of a directed graph.
 */
public class StronglyConnectedComponent {


    private static Integer[] end;
    /**
     * Find strongly connected component list.
     *
     * Time complexity : O(n+m)
     *
     * @param graph the graph
     * @return the list
     */
    public static List<Set> findStronglyConnectedComponent(IDirectedGraph graph)
    {
        IDirectedGraph reverse = graph.computeInverse();
        DFS dfs = new DFS(graph);
        end = dfs.memoizeEnd();
        Integer[] cloneEnd = end.clone();
        Arrays.sort(end, Collections.reverseOrder());

       return getStronglyConnectedComponents(reverse, cloneEnd);
    }

    private static List<Set> getStronglyConnectedComponents(IDirectedGraph graph, Integer[] cloneEnd)
    {
        List<Set> connectedComponents = new ArrayList<>();

        Set<DirectedNode> reached = new HashSet<>();
        for(Integer index : end){
            DirectedNode node = (DirectedNode) graph.getNodes().get(Arrays.asList(cloneEnd).indexOf(index));
            if(!reached.contains(node)){
                connectedComponents.add(addConnection(node, reached, new HashSet<>()));
            }
        }

        return connectedComponents;
    }

    /**
     * Link a node to its strongly connected component
     * @param s the current node that need to find his strongly connected component
     * @param a the strongly connected component
     * @param components the other component
     * @return a Set of Node, representation of the strongly connected component
     */
    private static Set<DirectedNode> addConnection(DirectedNode s, Set<DirectedNode> a, HashSet<DirectedNode> components) {
        a.add(s);
        components.add(s);
        for(DirectedNode succ : s.getSuccs()){
            if(!a.contains(succ)) {
                addConnection(succ, a, components);
            }
        }
        return components;
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main (String[] args)
    {
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);

        DirectedGraph al = new DirectedGraph(Matrix);

        List<Set> result = findStronglyConnectedComponent(al);

        System.out.println(result);
    }
}
