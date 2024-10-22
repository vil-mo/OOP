package ru.nsu.berezin.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AdjListGraph<T> implements Graph<T, Integer> {

    private class Edge {

        final int node;
        final int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    private final List<T> nodes = new ArrayList<>();
    private final List<List<Edge>> edges = new ArrayList<>();

    @Override
    public Integer addNode(T node) {
        if (node == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        int id = 0;
        for (T o : nodes) {
            if (o == null) {
                break;
            }
            id++;
        }
        if (id == nodes.size()) {
            edges.add(new ArrayList<>());
        } else {
            edges.get(id).clear();
        }
        return id;
    }

    @Override
    public T getNode(Integer id) {
        return nodes.get(id);
    }

    @Override
    public T removeNode(Integer id) {
        for (int i = 0; i < edges.size(); i++) {
            if (i == id) {
                edges.get(i).clear();
            } else {
                edges.get(i).removeIf(o -> Objects.equals(o, id));
            }
        }

        T data = nodes.get(id);
        nodes.set(id, null);
        return data;
    }

    @Override
    public Iterable<Integer> nodes() {
        return () -> {
            var iterator = new Iterator<Integer>() {
                int i = 0;

                void toNonNull() {
                    while (i < nodes.size() && nodes.get(i) == null) {
                        i++;
                    }
                }

                @Override
                public boolean hasNext() {
                    toNonNull();
                    return i < nodes.size();
                }

                @Override
                public Integer next() {
                    int result = i;
                    toNonNull();
                    return result;
                }
            };
            iterator.toNonNull();
            return iterator;
        };
    }

    @Override
    public void addEdge(Integer from, Integer to, int weight) {
        edges.get(from).add(new Edge(to, weight));
    }

    @Override
    public Optional<Integer> getEdge(Integer from, Integer to) {
        for (Edge edge : edges.get(from)) {
            if (edge.node == to) {
                return Optional.of(edge.weight);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> removeEdge(Integer from, Integer to) {
        for (int i = 0; i < edges.get(from).size(); i++) {
            if (edges.get(from).get(i).node == to) {
                return Optional.of(edges.get(from).remove(i).weight);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Integer> neighbors(Integer id) {
        List<Integer> list = new ArrayList<>(edges.get(id).size());
        for (Edge edge : edges.get(id)) {
            list.add(edge.node);
        }
        return list;
    }


    public static <Nt, Nindex> Graph<Nt, Nindex> reserveNodes(int amount) {
        return new AdjListGraph();
    }
}
