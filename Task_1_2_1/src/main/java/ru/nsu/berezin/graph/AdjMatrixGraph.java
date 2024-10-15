package ru.nsu.berezin.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AdjMatrixGraph<T> implements Graph<T, Integer> {

    private final boolean[] edges;
    private final Object[] nodes;

    /**
     * Creates a new matrix graph.
     *
     * @param maxNodesAmount Maximum amount of nodes in the graph
     */
    public AdjMatrixGraph(int maxNodesAmount) {
        edges = new boolean[maxNodesAmount * maxNodesAmount];
        nodes = new Object[maxNodesAmount];
    }

    @Override
    public Integer addNode(T data) throws IllegalArgumentException, IllegalStateException {
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
    public T getNode(Integer id) {
        return (T) nodes[id];
    }

    @Override
    public T removeNode(Integer id) {
        for (int i = 0; i < nodes.length; i++) {
            edges[id * nodes.length + i] = false;
            edges[i * nodes.length + id] = false;
        }

        T data = (T) nodes[id];
        nodes[id] = null;
        return data;
    }

    @Override
    public Iterable<Integer> nodes() {
        return () -> new Iterator<Integer>() {
            int i = 0;

            private void toNonNull() {
                while (i < nodes.length && nodes[i] == null) {
                    i++;
                }
            }

            @Override
            public boolean hasNext() {
                toNonNull();
                return i < nodes.length;
            }

            @Override
            public Integer next() {
                toNonNull();
                return i;
            }
        };
    }

    @Override
    public boolean addEdge(Integer from, Integer to) {
        boolean result = edges[from * nodes.length + to];
        edges[from * nodes.length + to] = true;
        return result;
    }

    @Override
    public boolean hasEdge(Integer from, Integer to) {
        return edges[from * nodes.length + to];
    }

    @Override
    public boolean removeEdge(Integer from, Integer to) {
        boolean result = edges[from * nodes.length + to];
        edges[from * nodes.length + to] = false;
        return result;
    }

    @Override
    public List<Integer> neighbors(Integer id) {
        List<Integer> result = new ArrayList();
        for (int i = 0; i < nodes.length; i++) {
            if (edges[id * nodes.length + i]) {
                result.add(i);
            }
        }
        return result;
    }
}
