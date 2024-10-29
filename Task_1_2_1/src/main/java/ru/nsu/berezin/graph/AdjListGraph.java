package ru.nsu.berezin.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

public class AdjListGraph<T> implements Graph<T, Integer> {

    private class Edge {

        final int node;
        final int weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        int node() {
            return node;
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

                @Override
                public boolean hasNext() {
                    while (i < nodes.size() && nodes.get(i) == null) {
                        i++;
                    }
                    return i < nodes.size();
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
        edges.get(from).add(new Edge(to, weight));
    }

    @Override
    public Iterable<Integer> getEdges(Integer from, Integer to) {
        return () -> {
            return new Iterator<Integer>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    while (i < edges.get(from).size() && edges.get(from).get(i).node != to) {
                        i++;
                    }
                    return i < edges.get(from).size();
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return edges.get(from).get(i).weight;
                }
            };
        };
    }

    @Override
    public void retainEdges(Integer from, Integer to, Predicate<Integer> predicate) {
        edges.get(from).removeIf(o -> o.node == to && predicate.test(o.weight));
    }

    @Override
    public Iterable<Integer> neighbors(Integer id) {
        return () -> {
            return edges.get(id).stream().map(Edge::node).iterator();
        };
    }

    public static <Nt, Nindex> Graph<Nt, Nindex> reserveNodes(int amount) {
        return new AdjListGraph();
    }
}
