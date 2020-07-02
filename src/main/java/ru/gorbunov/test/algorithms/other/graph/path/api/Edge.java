package ru.gorbunov.test.algorithms.other.graph.path.api;

import java.util.Objects;

public final class Edge {
    private static final String VALIDATION_MSG = "Vertex should not be null";

    private final Vertex from;
    private final Vertex to;

    public Edge(Vertex from, Vertex to) {
        this.from = Objects.requireNonNull(from, VALIDATION_MSG);
        this.to = Objects.requireNonNull(to, VALIDATION_MSG);
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }
}
