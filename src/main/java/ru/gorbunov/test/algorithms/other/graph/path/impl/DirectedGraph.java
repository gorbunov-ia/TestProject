package ru.gorbunov.test.algorithms.other.graph.path.impl;

import ru.gorbunov.test.algorithms.other.graph.path.api.Edge;

public class DirectedGraph extends AbstractGraphImpl {

    @Override
    protected void addEdgeToStorage(Edge edge) {
        getNeighbours(edge.getFrom()).add(edge.getTo());
    }
}
