package ru.gorbunov.test.algorithms.structures.tree;

import java.io.InputStream;
import java.util.Scanner;

final class TreeCreator {

    static TreeNode createTree(InputStream in) {
        Scanner scanner = new Scanner(in);
        final int size = scanner.nextInt();
        if (size == 0) {
            return null;
        }
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
}
