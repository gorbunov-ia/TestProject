package ru.gorbunov.test.algorithms.structures.tree;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Проверить, является ли данное двоичное дерево деревом поиска.
 * <p>
 * Т.е. верно ли, что для любой вершины дерева её ключ
 * больше всех ключей в левом поддереве данной вершины и
 * не больше всех ключей в правом поддереве.
 */
public class CorrectTreeChecker {

    public static void main(String[] args) {
//        new CorrectTreeChecker().process(System.in);
//        new CorrectTreeChecker().process(new ByteArrayInputStream(("3\n" +
//                "2 1 2\n" +
//                "1 -1 -1\n" +
//                "3 -1 -1").getBytes()));
        new CorrectTreeChecker().process(new ByteArrayInputStream(("6\n" +
                "4 1 2\n" +
                "1 -1 3\n" +
                "5 -1 -1\n" +
                "2 -1 4\n" +
                "3 5 -1\n" +
                "2 -1 -1").getBytes()));
    }

    private void process(InputStream inputStream) {
        TreeNode tree = TreeCreator.createTree(inputStream);
        System.out.println(check(tree) ? "CORRECT" : "INCORRECT");
    }

    private static boolean check(TreeNode tree) {
        if (tree == null) {
            return true;
        }
        LinkedList<AbstractMap.SimpleEntry<Integer, Integer>> keys = new LinkedList<>();
        try {
            iterate(tree, keys, 0);
        } catch (IllegalStateException ex) {
            return false;
        }
        return true;
    }

    private static void iterate(TreeNode root, List<AbstractMap.SimpleEntry<Integer, Integer>> accumulator, int rightCounter) {
        if (root == null) {
            return;
        }
        iterate(root.getLeft(), accumulator, rightCounter);
        if (!accumulator.isEmpty()) {
            AbstractMap.SimpleEntry<Integer, Integer> lastValue = accumulator.get(accumulator.size() - 1);
            if (lastValue.getKey() > root.getValue()
                    || lastValue.getKey() == root.getValue() && lastValue.getValue() >= rightCounter) {
                throw new IllegalStateException();
            }
        }
        accumulator.add(new AbstractMap.SimpleEntry<>(root.getValue(), rightCounter));
        iterate(root.getRight(), accumulator, rightCounter + 1);
    }
}
