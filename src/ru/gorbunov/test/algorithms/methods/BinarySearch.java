package ru.gorbunov.test.algorithms.methods;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * В первой строке даны целое число 1≤n≤105 и массив A[1…n] из n различных натуральных чисел, не превышающих 10^9,
 * в порядке возрастания, во второй — целое число 1≤k≤10^5 и k натуральных чисел b1,…,bk, не превышающих 10^9.
 * Для каждого i от 1 до k необходимо вывести индекс 1≤j≤n, для которого A[j]=b_i, или −1, если такого j нет.
 */
public class BinarySearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new ByteArrayInputStream("5 1 5 8 12 13\n5 8 1 23 1 11".getBytes()));
//        Scanner scanner = new Scanner(System.in);
        int[] m = getArray(scanner.nextInt(), scanner.nextLine());
        int[] k = getArray(scanner.nextInt(), scanner.nextLine());
        System.out.print(search(m, k[0]));
        for (int i = 1; i < k.length; i++) {
            System.out.print(" " + search(m, k[i]));
        }
    }

    private static int search(int[] array, int key) {
        final int index = Arrays.binarySearch(array, key);
        if (index >= 0) {
            return index + 1;
        }
        return -1;
    }

    private static int[] getArray(int length, String str) {
        return Arrays.stream(str.trim().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}
