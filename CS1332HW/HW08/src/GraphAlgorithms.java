import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Your implementation of various graph algorithms.
 *
 * @author Kevin Huynh
 * @version 1.0
 * @userid khuynh44 (i.e. gburdell3)
 * @GTID 903550619 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class GraphAlgorithms {

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start or graph cannot"
                    + " be null or graph does not contain start.");
        }
        List<Vertex<T>> list = new ArrayList<>();
        dfsHelper(start, graph, list);
        return list;
    }

    /**
     * The recursive helper method for DFS algorithm.
     * @param curr The current vertex the algorithm recurses through
     * @param graph The graph being recursed in.
     * @param list The list containing the ordered vertexes.
     * @param <T> The type of data the vertex and graph holds.
     */
    private static <T> void dfsHelper(Vertex<T> curr, Graph<T> graph,
                                List<Vertex<T>> list) {
        list.add(curr);
        Map<Vertex<T>, List<VertexDistance<T>>> map = graph.getAdjList();
        List<VertexDistance<T>> arr = map.get(curr);
        for (int i = 0; i < arr.size(); i++) {
            if (!list.contains(arr.get(i).getVertex())) {
                dfsHelper(arr.get(i).getVertex(), graph, list);
            }
        }

    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start or graph"
                    + " cannot be null or graph does not contain start.");
        }
        Set<Vertex<T>> vs = new HashSet<>();
        Map<Vertex<T>, Integer> dm = new HashMap<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        pq.add(new VertexDistance<>(start, 0));
        Map<Vertex<T>, List<VertexDistance<T>>> map = graph.getAdjList();
        while (!pq.isEmpty() && vs.size() != graph.getVertices().size()) {
            VertexDistance<T> temp = pq.remove();
            if (!vs.contains(temp.getVertex())) {
                vs.add(temp.getVertex());
                dm.put(temp.getVertex(), temp.getDistance());
                List<VertexDistance<T>> arr = map.get(temp.getVertex());
                for (VertexDistance<T> w: arr) {
                    pq.add(new VertexDistance<>(w.getVertex(),
                            w.getDistance() + temp.getDistance()));
                }
            }
        }
        return dm;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null
                || !graph.getVertices().contains(start)) {
            throw new IllegalArgumentException("Start or graph"
                    + " cannot be null or graph does not contain start.");
        }
        Set<Vertex<T>> vs = new HashSet<>();
        Set<Edge<T>> mst = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> map = graph.getAdjList();
        Set<Edge<T>> edges = graph.getEdges();
        for (Edge<T> edge: edges) {
            if (edge.getU().equals(start)) {
                pq.add(edge);
            }
        }
        vs.add(start);
        while (!pq.isEmpty() && vs.size() != graph.getVertices().size()) {
            Edge<T> edge = pq.remove();
            if (!vs.contains(edge.getV())) {
                vs.add(edge.getV());
                mst.add(edge);
                mst.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));
                for (Edge<T> ed: edges) {
                    if (ed.getU().equals(edge.getV())
                            && !vs.contains(ed.getV())) {
                        pq.add(ed);
                    }
                }
            }
        }
        return mst;
    }
}
