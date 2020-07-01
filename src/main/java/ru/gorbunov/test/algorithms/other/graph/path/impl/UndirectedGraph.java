package ru.gorbunov.test.algorithms.other.graph.path.impl;

import ru.gorbunov.test.algorithms.other.graph.path.api.Edge;
import ru.gorbunov.test.algorithms.other.graph.path.api.Graph;
import ru.gorbunov.test.algorithms.other.graph.path.api.Vertex;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UndirectedGraph implements Graph {

    private final Map<Vertex, List<Vertex>> graphStorage = new HashMap<>();

    @Override
    public void addVertex(Vertex vertex) {
        if (vertex == null) {
            throw new NullPointerException("Vertex should not be null");
        }
        graphStorage.put(vertex, new LinkedList<>());
    }

    @Override
    public void addEdge(Edge edge) {
        validateEdge(edge);

        graphStorage.get(edge.getFrom()).add(edge.getTo());
        graphStorage.get(edge.getTo()).add(edge.getFrom());
    }

    @Override
    public List<Edge> getPath(Vertex from, Vertex to) {
        validateVertexes(from, to);

        return Collections.emptyList();
    }

    private void validateVertexes(Vertex from, Vertex to) {
        if (from == null || to == null) {
            throw new NullPointerException("Vertexes should not be null");
        }
        assertContains(from);
        assertContains(to);
    }

    private void validateEdge(Edge edge) {
        if (edge == null) {
            throw new NullPointerException("Edge should not be null");
        }
        assertContains(edge.getFrom());
        assertContains(edge.getTo());
    }

    private void assertContains(Vertex vertex) {
        if (!graphStorage.containsKey(vertex)) {
            throw new IllegalArgumentException("Graph does not contain vertex " + vertex);
        }
    }
}
