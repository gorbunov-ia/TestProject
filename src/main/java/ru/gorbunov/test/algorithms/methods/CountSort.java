package ru.gorbunov.test.algorithms.methods;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Первая строка содержит число 1≤n≤10^4, вторая — n натуральных чисел, не превышающих 10.
 * Выведите упорядоченную по неубыванию последовательность этих чисел.
 */
public class CountSort {

    private static final int MAX_DIGIT = 10;

    public static void main(String[] args) {
        final int[] source = getArray();
        final int[] elements = getElements();

        for (int digit : source) {
            elements[digit - 1] = ++elements[digit - 1];
        }

        for (int i = 1; i < elements.length; i++) {
            elements[i] = elements[i - 1] + elements[i];
        }

        final int[] result = new int[source.length];
        for (int i = source.length - 1; i >= 0; i--) {
            int index = source[i] - 1;
            result[elements[index] - 1] = source[i];
            elements[index] = --elements[index];
        }

        StringBuilder builder = new StringBuilder(result.length);
        builder.append(result[0]);
        for (int i = 1; i < result.length; i++) {
            builder.append(" ").append(result[i]);
        }
        System.out.println(builder.toString());
    }

    private static int[] getElements() {
        return new int[MAX_DIGIT];
    }

    private static int[] getArray() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("5\n2 3 9 2 9".getBytes()));
//        Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        final int[] source = new int[length];
        for (int i = 0; i < source.length; i++) {
            source[i] = scanner.nextInt();
        }
        return source;
    }


}
