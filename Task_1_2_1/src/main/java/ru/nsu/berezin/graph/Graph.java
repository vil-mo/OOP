package ru.nsu.berezin.graph;

import java.util.function.Predicate;

/**
 * Collection of nodes connected by edges. Each node can store a data. Nodes and
 * edges are indexed.
 *
 * @param <T> Type of data stored in nodes
 * @param <NodeIndex> Type of index for nodes
 */
public interface Graph<T, NodeIndex> {

    /**
     * Adds a node to the graph.
     *
     * @param data Data to store in the node
     * @return Index of the new node
     * @throws IllegalArgumentException if argument is null
     */
    public NodeIndex addNode(T data) throws IllegalArgumentException;

    /**
     * Returns data of a node.
     *
     * @param id Index of the node
     * @return Data of the node.
     */
    public T getNode(NodeIndex id);

    /**
     * Removes a node from the graph. All edges connected to the node will be
     * removed as well. Node index would be invalid after this operation and
     * sholud not be used. If invalid index used, methods that were invoked may
     * or may not return invalid result and may or may not throw an undocumented
     * exception.
     *
     * @param id Index of the node to remove
     * @return Data of removed node. Empty if node was not found in the graph.
     */
    public T removeNode(NodeIndex id);

    /**
     * Iterates over all nodes in the graph.
     *
     * @return Iterator over all nodes in the graph
     */
    public Iterable<NodeIndex> nodes();

    /**
     * Adds an edge between two nodes.
     *
     * @param from Edge from this node
     * @param to Edge to this node
     * @param weight Weight of nodes
     */
    public void addEdge(NodeIndex from, NodeIndex to, int weight);

    /**
     * Get weight of all edges between two nodes.
     *
     * @param from Edge from this node
     * @param to Edge to this node
     * @return Iterator over all weights of edges between two nodes
     */
    public Iterable<Integer> getEdges(NodeIndex from, NodeIndex to);

    /**
     * Iterates over all edges between two nodes that satisfy the predicate.
     *
     * @param from Edge from this node
     * @param to Edge to this node
     * @param predicate Removes
     */
    public void retainEdges(NodeIndex from, NodeIndex to, Predicate<Integer> predicate);

    /**
     * Neighbors of a node.
     *
     * @param id Index of the node
     * @return List of neighbors of the node
     */
    public Iterable<NodeIndex> neighbors(NodeIndex id);

    /**
     * Returns this graph with enouth memory to hold amount nodes. No nodes have
     * been inserted in the graph yet. It is safe to cast resulting type to the
     * type of graph.
     *
     * @param amount resulting graph has enough capasity to hold this many
     *     nodes.
     */
    public static <Nt, Nindex> Graph<Nt, Nindex> reserveNodes(int amount) {
        throw new UnsupportedOperationException();
    }
}
