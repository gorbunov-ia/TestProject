package ru.gorbunov.test.algorithms.other.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Обход дерева в глубину. Пример через итератор.
 */
public class DepthFirstSearch {
    public static void main(String[] args) {
        new DepthFirstSearch().process();
    }

    private void process() {
        final Node tree = createBinaryTree();
        final NodeIterator iterator = new NodeIterator(tree);
        iterator.forEachRemaining(System.out::println);
    }

    private Node createBinaryTree() {
        final Node node11 = new Node(11);
        final Node node10 = new Node(10);
        final Node node9 = new Node(9);
        final Node node8 = new Node(node9, node10, 8);
        final Node node7 = new Node(node8, node11, 7);
        final Node node6 = new Node(6);
        final Node node5 = new Node(5);
        final Node node4 = new Node(4);
        final Node node3 = new Node(node4, node5, 3);
        final Node node2 = new Node(node3, node6, 2);
        return new Node(node2, node7, 1);
    }

    private static class NodeIterator implements Iterator<Node> {
        final LinkedList<Node> stack = new LinkedList<>();

        NodeIterator(Node root) {
            pushIfExists(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Node next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            final Node nextNode = stack.pop();
            pushIfExists(nextNode.getRight());
            pushIfExists(nextNode.getLeft());
            return nextNode;
        }

        private void pushIfExists(Node node) {
            if (node != null) {
                stack.push(node);
            }
        }
    }

}
