package ru.gorbunov.test.algorithms.structures.tree;

class TreeNode {

    private TreeNode left;
    private TreeNode right;
    private int value;

    TreeNode() {
        this(null, null, -1);
    }

    TreeNode(TreeNode left, TreeNode right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    TreeNode(int value) {
        this(null, null, value);
    }

    TreeNode getLeft() {
        return left;
    }

    void setLeft(TreeNode left) {
        this.left = left;
    }

    TreeNode getRight() {
        return right;
    }

    void setRight(TreeNode right) {
        this.right = right;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }
}
