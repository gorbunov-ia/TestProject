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
    void testNullFromArgument() {
        final Vertex first = new Vertex(0);
        graph.addVertex(first);

        Assertions.assertThrows(NullPointerException.class, () -> graph.getPath(null, first));
    }

    @Test
    void testNullToArgument() {
        final Vertex first = new Vertex(0);
        graph.addVertex(first);

        Assertions.assertThrows(NullPointerException.class, () -> graph.getPath(first, null));
    }

    @Test
    void testUnknownVertex() {
        final Vertex first = new Vertex(0);
        graph.addVertex(first);
        final Vertex second = new Vertex(1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> graph.getPath(first, second));
    }

    @Test
    void testNoEdge() {
        final Vertex first = new Vertex(0);
        graph.addVertex(first);
        final Vertex second = new Vertex(1);
        graph.addVertex(second);
        final Vertex third = new Vertex(2);
        graph.addVertex(third);

        graph.addEdge(new Edge(first, third));

        final List<Edge> path = graph.getPath(first, second);

        Assertions.assertEquals(0, path.size());
    }

    @Test
    void testSearchingPath() {
        final Vertex first = new Vertex(10);
        graph.addVertex(first);
        final Vertex second = new Vertex(20);
        graph.addVertex(second);
        final Vertex third = new Vertex(30);
        graph.addVertex(third);
        final Vertex fourth = new Vertex(40);
        graph.addVertex(fourth);

        graph.addEdge(new Edge(first, first));
        graph.addEdge(new Edge(first, second));
        graph.addEdge(new Edge(second, fourth));
        graph.addEdge(new Edge(second, third));

        final List<Edge> path = graph.getPath(first, third);

        assertPath(path, first, third);
    }

    private void assertPath(List<Edge> path, Vertex from, Vertex to) {
        Assertions.assertNotEquals(0, path.size());

        Vertex previous = from;
        for (Edge edge : path) {
            Assertions.assertEquals(previous, edge.getFrom());
            previous = edge.getTo();
        }
        Assertions.assertEquals(to, previous);
    }
}