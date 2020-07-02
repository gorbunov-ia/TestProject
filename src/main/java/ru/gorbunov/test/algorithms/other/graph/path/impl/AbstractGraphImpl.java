package ru.gorbunov.test.algorithms.other.graph.path.impl;

import ru.gorbunov.test.algorithms.other.graph.path.api.Edge;
import ru.gorbunov.test.algorithms.other.graph.path.api.Graph;
import ru.gorbunov.test.algorithms.other.graph.path.api.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

abstract class AbstractGraphImpl implements Graph {
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

        addEdgeToStorage(edge);
    }

    protected abstract void addEdgeToStorage(Edge edge);

    @Override
    public List<Edge> getPath(Vertex from, Vertex to) {
        validateVertexes(from, to);

        Set<Vertex> visitedVertexes = new HashSet<>();

        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Edge> path = new HashMap<>();
        queue.add(from);

        while (!queue.isEmpty()) {

            final Vertex current = queue.poll();

            if (current.equals(to)) {
                return restorePath(path, to);
            }

            visitedVertexes.add(current);
            List<Vertex> neighbours = getNeighbours(current);

            for (Vertex vertex : neighbours) {
                if (!visitedVertexes.contains(vertex)) {
                    path.put(vertex, new Edge(current, vertex));
                    queue.add(vertex);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Edge> restorePath(Map<Vertex, Edge> edges, Vertex vertex) {
        List<Edge> path = new ArrayList<>();

        Edge edge = edges.get(vertex);
        while (edge != null) {
            path.add(edge);
            edge = edges.get(edge.getFrom());
        }

        Collections.reverse(path);
        return path;
    }

    protected List<Vertex> getNeighbours(Vertex vertex) {
        return graphStorage.get(vertex);
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
