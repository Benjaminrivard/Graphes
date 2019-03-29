package GraphAlgorithms;

import Abstraction.AbstractNode;
import Abstraction.IGraph;
import AdjacencyList.DirectedGraph;
import AdjacencyList.UndirectedGraph;

import java.util.*;

/**
 * The type Dfs.
 */
public class DFS {

    private IGraph graph;
    private int compteur;
    private int[] visite, debut, fin;

    /**
     * Instantiates a new Dfs.
     *
     * @param graph the graph
     */
    public DFS(IGraph graph) {
        this.graph = graph;
    }

    /**
     * Explore graph list.
     *
     * Time complexity : O(n+m)
     *
     * @return the list
     */
    public List<AbstractNode> exploreGraph() {
        Set<AbstractNode> atteint = new HashSet<>();
        List<AbstractNode> nodeOrderedByVisit = new ArrayList<>();
        for (AbstractNode s : (List<AbstractNode>) this.graph.getNodes()) {
            if (!atteint.contains(s)) {
                nodeOrderedByVisit.add(s);
                explorerSommet(s, atteint, nodeOrderedByVisit);
            }
        }
        return nodeOrderedByVisit;
    }

    /**
     * Explorer sommet.
     *
     * @param s    the s
     * @param a    the a
     * @param list the list
     */
    private void explorerSommet(AbstractNode s, Set<AbstractNode> a, List<AbstractNode> list) {
        a.add(s);
        for (AbstractNode t : (List<AbstractNode>) graph.getNeighbors(s)) {
            if (!a.contains(t)) {
                list.add(t);
                explorerSommet(t,a, list);
            }
        }
    }


    /**
     * Memoize end int [ ].
     * Same time complexity as the normal version of the DFS.
     *
     * @return the int [ ]
     */
    public Integer[] memoizeEnd()
    {
        Set<AbstractNode> atteint = new HashSet<>();
        int compteur =0;
        visite = new int[graph.getNbNodes()];
        debut = new int[graph.getNbNodes()];
        fin = new int[graph.getNbNodes()];
        for (AbstractNode s : (List<AbstractNode>) this.graph.getNodes()) {
            if (!atteint.contains(s)) {
                exploreMemoize(s, atteint);
            }
        }
        return Arrays.stream( fin ).boxed().toArray( Integer[]::new );
    }

    /**
     * Explorer sommet.
     *
     * @param s the s
     * @param a the a
     */
    private void exploreMemoize(AbstractNode s, Set<AbstractNode> a) {
        a.add(s);
        visite[s.getLabel()] = 1;
        debut[s.getLabel()] = compteur++;
        for (AbstractNode t : (List<AbstractNode>) graph.getNeighbors(s)) {
            if (!a.contains(t)) {
                exploreMemoize(t,a);
            }
        }
        fin[s.getLabel()] = compteur++;
        visite[s.getLabel()] = 2;
    }






    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        int[][] Matrix = GraphTools.generateGraphData(10, 20, false, false, false, 100001);

        DirectedGraph al = new DirectedGraph(Matrix);

        UndirectedGraph au = new UndirectedGraph(Matrix);

        DFS directed = new DFS(al);
        DFS undirected = new DFS (au);

        System.out.println("Graphe dirigé : "+directed.exploreGraph());

        System.out.println("Graphe non dirigé :"+undirected.exploreGraph());

        System.out.println("fin[] : " +Arrays.toString(directed.memoizeEnd()) );

    }

    public int getCompteur() {
        return compteur;
    }

    public int[] getVisite() {
        return visite;
    }

    public int[] getDebut() {
        return debut;
    }

    public int[] getFin() {
        return fin;
    }
}
