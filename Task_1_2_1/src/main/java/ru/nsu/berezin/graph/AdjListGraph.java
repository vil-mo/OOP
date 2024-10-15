package ru.nsu.berezin.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class AdjListGraph<T> implements Graph<T, Integer> {

    private final List<T> nodes = new ArrayList<>();
    private final List<List<Integer>> edges = new ArrayList<>();

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
    public void addEdge(Integer from, Integer to) {
        edges.get(from).add(to);
    }

    @Override
    public boolean hasEdge(Integer from, Integer to) {
        return edges.get(from).contains(to);
    }

    @Override
    public boolean removeEdge(Integer from, Integer to) {
        return edges.get(from).remove(to);
    }

    @Override
    public List<Integer> neighbors(Integer id) {
        return edges.get(id);
    }

}
