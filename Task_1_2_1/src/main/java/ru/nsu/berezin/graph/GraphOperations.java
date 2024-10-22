package ru.nsu.berezin.graph;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Class that contains varous operations that can be applied to `Graph`s.
 */
public abstract class GraphOperations {

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
        List<NodeIndex> result = new ArrayList<>();
        HashSet<NodeIndex> visited = new HashSet<>();

        for (NodeIndex node : graph.nodes()) {
            topSortRecursion(graph, node, visited, result);
        }

        return result;
    }

    /**
     * Loads graph from file. Format:
     *
     * <pre>
     * number_of_nodes number_of_edges
     * data_1
     * data_2
     * ...
     * node_1 node_2
     * node_3 node_4
     * ...
     * </pre> First line contains number of nodes and number of edges in the
     * graph. Then for number of nodes lines, there is a data for each node,
     *
     * @param path Path to file
     * @return Loaded graph
     * @throws IOException If file cannot be read
     * @throws ParseException If file is not in correct format
     */
    public static <G extends Graph<T, NodeIndex>, T, NodeIndex>
            G loadFromFile(Parser<T> p, String path) throws IOException, ParseException {
        File file = new File(path);
        Reader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);

        String[] firstLine = scanner.nextLine().split(" ");
        if (firstLine.length != 2) {
            throw new ParseException("First line must contain two numbers", 0);
        }
        int nodesAmount, edgesAmount;
        try {
            nodesAmount = Integer.parseInt(firstLine[0]);
            edgesAmount = Integer.parseInt(firstLine[1]);
        } catch (NumberFormatException e) {
            throw new ParseException("First line must contain two numbers", 0);
        }

        G graph = (G) G.reserveNodes(nodesAmount);
        List<NodeIndex> nodes = new ArrayList<>();

        for (int i = 0; i < nodesAmount; i++) {
            String data = scanner.nextLine();
            T parsedData = p.parse(data);
            NodeIndex node = graph.addNode(parsedData);
            nodes.add(node);
        }

        for (int i = 0; i < edgesAmount; i++) {
            String[] edge = scanner.nextLine().split(" ");
            if (edge.length != 2) {
                throw new ParseException("Edge must contain two numbers", i + nodesAmount + 1);
            }
            try {
                int from = Integer.parseInt(edge[0]);
                int to = Integer.parseInt(edge[1]);
                graph.addEdge(nodes.get(from), nodes.get(to));
            } catch (NumberFormatException e) {
                throw new ParseException("Edge must contain two numbers", i + nodesAmount + 1);
            }
        }

        return graph;
    }
}
