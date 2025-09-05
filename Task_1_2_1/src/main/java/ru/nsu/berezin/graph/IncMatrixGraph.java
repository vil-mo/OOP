package ru.nsu.berezin.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

/**
 * A graph implementation based on an incident matrix.
 *
 * @param <V> Type of the vertices.
 */
public class IncMatrixGraph<V> implements Graph<V, Integer> {

    private final Object[] nodes;
    private final List<Integer>[] edges;

    /**
     * Creates a new graph.
     *
     * @param maxNodesAmount Maximum amount of nodes in the graph
     */
    public IncMatrixGraph(int maxNodesAmount) {
        nodes = new Object[maxNodesAmount];
        edges = new ArrayList[maxNodesAmount * maxNodesAmount];
    }

    @Override
    public Integer addNode(V data) throws IllegalArgumentException, IllegalStateException {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        int id = 0;
        for (Object o : nodes) {
            if (o == null) {
                break;
            }
            id++;
        }
        if (id == nodes.length) {
            throw new IllegalStateException("Graph is full");
        }

        nodes[id] = data;
        return id;
    }

    @Override
    public V getNode(Integer id) {
        return (V) nodes[id];
    }

    @Override
    public V removeNode(Integer id) {
        for (int i = 0; i < nodes.length; i++) {
            edges[id * nodes.length + i].clear();
            edges[i * nodes.length + id].clear();
        }
        V data = (V) nodes[id];
        nodes[id] = null;
        return data;
    }

    @Override
    public Iterable<Integer> nodes() {
        return () -> {
            var iterator = new Iterator<Integer>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    while (i < nodes.length && nodes[i] == null) {
                        i++;
                    }
                    return i < nodes.length;
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return i;
                }
            };
            return iterator;
        };
    }

    @Override
    public void addEdge(Integer from, Integer to, int weight) {
        edges[from * nodes.length + to].add(weight);
    }

    @Override
    public Iterable<Integer> getEdges(Integer from, Integer to) {
        return () -> {
            return edges[from * nodes.length + to].iterator();
        };
    }

    @Override
    public void retainEdges(Integer from, Integer to, Predicate<Integer> predicate) {
        edges[from * nodes.length + to].removeIf(predicate);
    }

    @Override
    public Iterable<Integer> neighbors(Integer id) {
        return () -> {
            return new Iterator<Integer>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    while (i < nodes.length && nodes[i] == null && edges[id * nodes.length + i].isEmpty()) {
                        i++;
                    }
                    return i < nodes.length;
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return i;
                }
            };
        };
    }

    public static <Nt, Nindex> Graph<Nt, Nindex> reserveNodes(int amount) {
        return new IncMatrixGraph(amount);
    }
}
