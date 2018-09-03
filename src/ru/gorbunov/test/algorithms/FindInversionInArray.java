package ru.gorbunov.test.algorithms;

import java.io.ByteArrayInputStream;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * Первая строка содержит число 1≤n≤105, вторая — массив A[1…n], содержащий натуральные числа, не превосходящие 10^9.
 * Необходимо посчитать число пар индексов 1≤i<j≤n, для которых A[i]>A[j]. (Такая пара элементов называется
 * инверсией массива. Количество инверсий в массиве является в некотором смысле его мерой неупорядоченности:
 * например, в упорядоченном по неубыванию массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
 * инверсию образуют каждые два элемента.)
 */
public class FindInversionInArray {

    public static void main(String[] args) {
        int[] digits = getArray();
        System.out.println(n2(digits));
        System.out.println(nLogN(digits));
    }

    private static int[] getArray() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("5\n2 3 9 2 9".getBytes()));
//        Scanner scanner = new Scanner(new ByteArrayInputStream("7\n7 6 5 4 3 2 1".getBytes()));
//        Scanner scanner = new Scanner(new ByteArrayInputStream("5\n1 2 3 5 4".getBytes()));
//        Scanner scanner = new Scanner(new ByteArrayInputStream("6\n1 3 4 5 6 2".getBytes()));
//        Scanner scanner = new Scanner(System.in);
        int[] result = new int[scanner.nextInt()];
        for (int i = 0; i < result.length; i++) {
            result[i] = scanner.nextInt();
        }
        return result;
    }

    private static long n2(int[] source) {
        int inversionCounter = 0;
        for (int i = 0; i < source.length - 1; i++) {
            for (int j = i + 1; j < source.length; j++) {
                if (source[i] > source[j]) {
                    inversionCounter++;
                }
            }
        }
        return inversionCounter;
    }

    private static long nLogN(int[] source) {
        final Queue<MergeResult> quque = new ArrayDeque<>(source.length);
        for (int i = 0; i < source.length; i = i + 2) {
            final MergeResult left = new MergeResult(source[i], false);
            final MergeResult right;
            if (i + 1 < source.length) {
                right = new MergeResult(source[i + 1], i + 2 == source.length);
            } else {
                right = new MergeResult();
            }
            quque.add(new MergeResult(left, right));
        }

        while (true) {
            MergeResult first = quque.poll();
            if (quque.isEmpty()) {
                return first.inversionCounter;
            }
            if (first.isLast) {
                quque.add(first);
                continue;
            }
            MergeResult second = quque.poll();
            quque.add(new MergeResult(first, second));
        }
    }


    private static final class MergeResult {

        private int[] result;
        private long inversionCounter;
        private boolean isLast;

        MergeResult() {
            result = new int[0];
            isLast = true;
        }

        MergeResult(int resultValue, boolean isLast) {
            result = new int[]{resultValue};
            this.isLast = isLast;
        }

        MergeResult(MergeResult left, MergeResult right) {
            int[] l = left.result;
            int[] r = right.result;

            result = new int[l.length + r.length];
            inversionCounter = left.inversionCounter + right.inversionCounter;
            isLast = left.isLast || right.isLast;

            int leftIndex = 0;
            int rightIndex = 0;
            for (int i = 0; i < result.length; i++) {
                if (leftIndex == l.length) {
                    result[i] = r[rightIndex++];
                    continue;
                }
                if (rightIndex == r.length) {
                    result[i] = l[leftIndex++];
                    continue;
                }

                if (l[leftIndex] > r[rightIndex]) {
                    result[i] = r[rightIndex++];
                    inversionCounter += l.length - leftIndex;
                } else {
                    result[i] = l[leftIndex++];
                }
            }
        }
    }

}
