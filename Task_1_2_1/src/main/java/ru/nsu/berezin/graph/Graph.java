package ru.nsu.berezin.graph;

import java.util.List;
import java.util.Optional;

/**
 * Collection of nodes connected by edges. Each node can store a data. Nodes and
 * edges are indexed.
 *
 * @param <T> Type of data stored in nodes
 * @param <NodeIndex> Type of index for nodes
 * @param <EdgeIndex> Type of index for edges
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
     * @return Data of the node. Empty if node was not found in the graph.
     */
    public Optional<T> getNode(NodeIndex id);

    /**
     * Removes a node from the graph.
     *
     * @param id Index of the node to remove
     * @return Data of removed node. Empty if node was not found in the graph.
     */
    public Optional<T> removeNode(NodeIndex id);

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
     * @return False if one of the nodes was not found in graph.
     */
    public boolean addEdge(NodeIndex from, NodeIndex to);

    /**
     * Returns true if the graph contains an edge between two nodes.
     *
     * @param from Edge from this node
     * @param to Edge to this node
     * @return True if the graph contains an edge between two nodes, false
     * otherwise.
     */
    public boolean hasEdge(NodeIndex from, NodeIndex to);

    /**
     * Removes an edge from the graph.
     *
     * @param from Edge from this node
     * @param to Edge to this node
     * @return True if edge was removed, false if edge was not found in the
     * graph
     */
    public boolean removeEdge(NodeIndex from, NodeIndex to);

    /**
     * Neighbors of a node.
     *
     * @param id Index of the node
     * @return List of neighbors of the node
     */
    public List<NodeIndex> neighbors(NodeIndex id);

}
