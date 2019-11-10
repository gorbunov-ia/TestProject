package ru.gorbunov.test.algorithms.structures.tree;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import static ru.gorbunov.test.algorithms.structures.tree.TreeCreator.createTree;

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
