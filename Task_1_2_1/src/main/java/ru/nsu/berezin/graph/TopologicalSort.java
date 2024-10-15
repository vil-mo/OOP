package ru.nsu.berezin.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class TopologicalSort {

    private static <T, NodeIndex> void topSortRecursion(
            Graph<T, NodeIndex> graph,
            NodeIndex node,
            HashSet<NodeIndex> visited,
            List<NodeIndex> result
    ) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);

        for (NodeIndex neighbor : graph.neighbors(node)) {
            topSortRecursion(graph, neighbor, visited, result);
        }

        result.add(node);
    }

    /**
     * Performs topological sort of the graph. Nodes are listed from the one
     * with minimum outgoing edges to the one with maximum outgoing edges.
     *
     * @return List of nodes in topological order.
     */
    public static <T, NodeIndex> List<NodeIndex> topologicalSort(Graph<T, NodeIndex> graph) {
        List<NodeIndex> result = new ArrayList();
        HashSet<NodeIndex> visited = new HashSet();

        for (NodeIndex node : graph.nodes()) {
            topSortRecursion(graph, node, visited, result);
        }

        return result;
    }
}
