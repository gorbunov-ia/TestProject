package ru.gorbunov.test.algorithms.other.graph.path.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.gorbunov.test.algorithms.other.graph.path.api.Edge;
import ru.gorbunov.test.algorithms.other.graph.path.api.Graph;
import ru.gorbunov.test.algorithms.other.graph.path.api.Vertex;

import java.util.List;

class UndirectedGraphTest {

    private Graph graph;

    @BeforeEach
    void setUp() {
        graph = new UndirectedGraph();
    }

    @Test
    void testNoEdge() {
        final Vertex first = new Vertex(0);
        graph.addVertex(first);
        final Vertex second = new Vertex(1);
        graph.addVertex(second);

        final List<Edge> path = graph.getPath(first, second);

        Assertions.assertEquals(0, path.size());
    }
}