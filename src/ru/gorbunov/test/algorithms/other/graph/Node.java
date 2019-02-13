package ru.gorbunov.test.algorithms.other.graph;

class Node {
    private final Node left;
    private final Node right;
    private final int value;

    Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    Node(int value) {
        this(null, null, value);
    }

    Node getLeft() {
        return left;
    }

    Node getRight() {
        return right;
    }

    int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
