package ru.gorbunov.test.algorithms.other.graph.path.api;

import ru.gorbunov.test.algorithms.other.graph.path.impl.DirectedGraph;
import ru.gorbunov.test.algorithms.other.graph.path.impl.UndirectedGraph;

public class GraphFactory {

    /**
     * Create a graph by type
     *
     * @param type type of graph
     * @return graph implementation
     */
    public Graph createGraph(GraphType type) {
        switch (type) {
            case DIRECTED:
                return new DirectedGraph();
            case UNDIRECTED:
                return new UndirectedGraph();
            default:
                throw new IllegalArgumentException("Unknown graph type \"" + type + "\"");
        }
    }

}
