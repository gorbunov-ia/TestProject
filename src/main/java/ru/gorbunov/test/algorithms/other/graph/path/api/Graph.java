package ru.gorbunov.test.algorithms.other.graph.path.api;

import java.util.List;

/**
 * Interface of a simple graph
 */
public interface Graph {
    /**
     * Adds vertex to the graph
     *
     * @param vertex new vertex
     */
    void addVertex(Vertex vertex);

    /**
     * Adds edge to the graph
     *
     * @param edge new edge
     */
    void addEdge(Edge edge);

    /**
     * Returns a list of edges between 2 vertices (path doesn’t have to be optimal)
     *
     * @param from start vertex
     * @param to   destination vertex
     * @return list of edges
     */
    List<Edge> getPath(Vertex from, Vertex to);
}
