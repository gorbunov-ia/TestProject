package ru.gorbunov.test.algorithms.structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Вычислить высоту данного дерева.
 */
public class TreeHeight {
    private Map<Integer, List<Integer>> childs = new HashMap<>();
    private int root;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        final int[] nodes = new int[length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = scanner.nextInt();
        }
        System.out.println(new TreeHeight().process(nodes));
    }

    private int process(int[] nodes) {
        if (nodes.length == 0) {
            return 0;
        }
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == -1) {
                root = i;
                continue;
            }
            final List<Integer> childList = childs.get(nodes[i]);
            if (childList != null) {
                childList.add(i);
            } else {
                final LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                childs.put(nodes[i], list);
            }
        }
        return height(root);
    }

    private int height(int node) {
        int height = 1;
        for (int child : getChild(node)) {
            height = Math.max(height, height(child) + 1);
        }
        return height;
    }

    private List<Integer> getChild(int parent) {
        final List<Integer> childList = childs.get(parent);
        return childList != null ? childList : Collections.emptyList();
    }

    @ParameterizedTest
    @MethodSource("createTestData2")
    public void test(List<Integer> input, int result) {
        Assertions.assertEquals(result, new TreeHeight().process(input.stream().mapToInt(Integer::intValue).toArray()));
    }

    private static Stream<Arguments> createTestData2() {
        return Stream.of(
                Arguments.of(Arrays.asList(4, -1, 4, 1, 1), 3),
                Arguments.of(Arrays.asList(9, 7, 5, 5, 2, 9, 9, 9, 2, -1), 4));
    }
}
