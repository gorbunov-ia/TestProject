package ru.gorbunov.test.algorithms.structures.tree;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 * Построить in-order, pre-order и post-order обходы данного двоичного дерева.
 * <p>
 * Формат входа. Первая строка содержит число вершин n. Вершины
 * дерева пронумерованы числами от 0 до n−1. Вершина 0 является
 * корнем. Каждая из следующих n строк содержит информацию о
 * вершинах 0,1,...,n−1: i-я строка задаёт числа key i , left i и right i,
 * где key i — ключ вершины i, left i — индекс левого сына верши-
 * ны i, а right i — индекс правого сына вершины i. Если у верши-
 * ны i нет одного или обоих сыновей, соответствующее значение
 * равно −1.
 */
public class TreeIterationExample {

    public static void main(String[] args) {
//        new TreeIterationExample().process(System.in);
        new TreeIterationExample().process(new ByteArrayInputStream(("5\n" +
                "4 1 2\n" +
                "2 3 4\n" +
                "5 -1 -1\n" +
                "1 -1 -1\n" +
                "3 -1 -1").getBytes()));
    }

    private void process(InputStream inputStream) {
        final TreeNode tree = createTree(inputStream);
        print(tree, Order.IN);
        print(tree, Order.PRE);
        print(tree, Order.POST);
    }

    private static TreeNode createTree(InputStream in) {
        Scanner scanner = new Scanner(in);
        final int size = scanner.nextInt();
        final TreeNode[] treeNodes = new TreeNode[size];
        for (int i = 0; i < size; i++) {
            final int key = scanner.nextInt();
            final int left = scanner.nextInt();
            final int right = scanner.nextInt();
            if (treeNodes[i] == null) {
                treeNodes[i] = new TreeNode();
            }
            TreeNode node = treeNodes[i];
            node.setValue(key);
            node.setLeft(resolve(left, treeNodes));
            node.setRight(resolve(right, treeNodes));
        }
        return treeNodes[0];
    }

    private static TreeNode resolve(int parent, TreeNode[] treeNodes) {
        if (parent == -1) {
            return null;
        }
        final TreeNode currentNode = treeNodes[parent];
        if (currentNode != null) {
            return currentNode;
        }
        TreeNode node = new TreeNode();
        treeNodes[parent] = node;
        return node;
    }

    private static void print(TreeNode root, Order order) {
        LinkedList<String> result = new LinkedList<>();
        order.calculate(root, result);
        System.out.println(String.join(" ", result));
    }

    private static void printInOrder(TreeNode root, List<String> accumulator) {
        if (root == null) {
            return;
        }
        printInOrder(root.getLeft(), accumulator);
        accumulator.add(String.valueOf(root.getValue()));
        printInOrder(root.getRight(), accumulator);
    }

    private static void printPreOrder(TreeNode root, List<String> accumulator) {
        if (root == null) {
            return;
        }
        accumulator.add(String.valueOf(root.getValue()));
        printPreOrder(root.getLeft(), accumulator);
        printPreOrder(root.getRight(), accumulator);
    }

    private static void printPostOrder(TreeNode root, List<String> accumulator) {
        if (root == null) {
            return;
        }
        printPostOrder(root.getLeft(), accumulator);
        printPostOrder(root.getRight(), accumulator);
        accumulator.add(String.valueOf(root.getValue()));
    }

    enum Order {
        IN(TreeIterationExample::printInOrder),
        PRE(TreeIterationExample::printPreOrder),
        POST(TreeIterationExample::printPostOrder);

        private final BiConsumer<TreeNode, List<String>> calculator;

        Order(BiConsumer<TreeNode, List<String>> function) {
            calculator = function;
        }

        void calculate(TreeNode root, List<String> accumulator) {
            calculator.accept(root, accumulator);
        }
    }
}
