package ru.nsu.berezin.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MatrixGraph<T extends Object> implements Graph<T, Integer> {

    private final boolean[] edges;
    private final Object[] nodes;

    /**
     * Creates a new matrix graph.
     *
     * @param maxNodesAmount Maximum amount of nodes in the graph
     */
    public MatrixGraph(int maxNodesAmount) {
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
    public Optional<T> getNode(Integer id) {
        if (id < 0 || id >= nodes.length) {
            return Optional.empty();
        }
        return Optional.ofNullable((T) nodes[id]);
    }

    @Override
    public Optional<T> removeNode(Integer id) {
        if (id < 0 || id >= nodes.length) {
            return Optional.empty();
        }
        T data = (T) nodes[id];
        nodes[id] = null;
        return Optional.ofNullable(data);
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
        if (from < 0 || from >= nodes.length) {
            return false;
        }
        if (to < 0 || to >= nodes.length) {
            return false;
        }

        edges[from * nodes.length + to] = true;
        return true;
    }

    @Override
    public boolean hasEdge(Integer from, Integer to) {
        if (from < 0 || from >= nodes.length) {
            return false;
        }
        if (to < 0 || to >= nodes.length) {
            return false;
        }

        return edges[from * nodes.length + to];
    }

    @Override
    public boolean removeEdge(Integer from, Integer to) {
        if (from < 0 || from >= nodes.length) {
            return false;
        }
        if (to < 0 || to >= nodes.length) {
            return false;
        }
        boolean result = edges[from * nodes.length + to];
        edges[from * nodes.length + to] = false;
        return result;
    }

    @Override
    public List<Integer> neighbors(Integer id) {
        if (id < 0 || id >= nodes.length) {
            return List.of();
        }

        List<Integer> result = new ArrayList();
        for (int i = 0; i < nodes.length; i++) {
            if (edges[id * nodes.length + i]) {
                result.add(i);
            }
        }
        return result;
    }
}
