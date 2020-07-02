package ru.gorbunov.test.algorithms.other.graph.path.impl;

import ru.gorbunov.test.algorithms.other.graph.path.api.Edge;

public class UndirectedGraph extends AbstractGraphImpl {

    @Override
    protected void addEdgeToStorage(Edge edge) {
        getNeighbours(edge.getFrom()).add(edge.getTo());
        if (!edge.getFrom().equals(edge.getTo())) {
            getNeighbours(edge.getTo()).add(edge.getFrom());
        }
    }
}
