package ru.gorbunov.test.algorithms.structures;

import java.io.ByteArrayInputStream;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Максимум в скользящем окне
 * Найти максимум в каждом окне размера m данного массива чисел
 * A[1...n].
 */
public class MaximumFromShiftWindow {
    private static final LinkedList<AbstractMap.SimpleEntry<Integer, Integer>> s1 = new LinkedList<>();
    private static final LinkedList<AbstractMap.SimpleEntry<Integer, Integer>> s2 = new LinkedList<>();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(new ByteArrayInputStream(getInput().getBytes()));
        final int digitsCount = scanner.nextInt();
        int[] digits = new int[digitsCount];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = scanner.nextInt();
        }
        final int windowSize = scanner.nextInt();
        final StringBuilder builder = new StringBuilder();

        pushToS1(digits, 0, windowSize);
        builder.append(getMax());

        for (int i = 0; i < digits.length - windowSize; i++) {
            pushToS1(digits, i + windowSize);
            builder.append(" ").append(getMax());
        }
        System.out.println(builder.toString());
    }

    private static int getMax() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                final AbstractMap.SimpleEntry<Integer, Integer> entry = s1.pop();
                s2.push(new AbstractMap.SimpleEntry<>(entry.getKey(), getMax(entry.getKey(), s2)));
            }
        }
        final AbstractMap.SimpleEntry<Integer, Integer> fromS2 = s2.pop();
        return s1.peek() == null ? fromS2.getValue() : Math.max(s1.peek().getValue(), fromS2.getValue());
    }

    private static void pushToS1(int[] digits, int indexFrom, int indexTo) {
        int i = 0;
        while (i < indexTo && indexFrom + i < digits.length) {
            pushToS1(digits, indexFrom + i);
            i++;
        }
    }

    private static void pushToS1(int[] digits, int index) {
        s1.push(new AbstractMap.SimpleEntry<>(digits[index], getMax(digits[index], s1)));
    }

    private static int getMax(int element, Queue<AbstractMap.SimpleEntry<Integer, Integer>> stack) {
        final AbstractMap.SimpleEntry<Integer, Integer> entry = stack.peek();
        if (entry == null) {
            return element;
        }
        return Math.max(element, entry.getValue());
    }

    private static String getInput() {
        return "8\n" +
                "2 7 3 1 5 2 6 2\n" +
                "4";
    }
}
