package ru.gorbunov.test.algorithms.other.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Обход дерева в ширину. Пример через итератор.
 */
public class BreadthFirstSearch {
    public static void main(String[] args) {
        new BreadthFirstSearch().process();
    }

    private void process() {
        final Node graph = createGraph();
        final Iterator<Node> iterator = new NodeIterator(graph);
        iterator.forEachRemaining(System.out::println);
    }

    private Node createGraph() {
        final Node node11 = new Node(11);
        final Node node10 = new Node(10);
        final Node node9 = new Node(9);
        final Node node8 = new Node(8);
        final Node node7 = new Node(7);
        final Node node6 = new Node(node10, node11, 6);
        final Node node5 = new Node(5);
        final Node node4 = new Node(node8, node9, 4);
        final Node node3 = new Node(node6, node7, 3);
        final Node node2 = new Node(node4, node5, 2);
        return new Node(node2, node3, 1);
    }

    private static class NodeIterator implements Iterator<Node> {
        final LinkedList<Node> nodes = new LinkedList<>();

        NodeIterator(Node root) {
            addNodeIfExists(root);
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final Node nextNode = nodes.poll();
            addNodeIfExists(nextNode.getLeft());
            addNodeIfExists(nextNode.getRight());
            return nextNode;
        }

        private void addNodeIfExists(Node node) {
            if (node != null) {
                nodes.add(node);
            }
        }
    }
}
