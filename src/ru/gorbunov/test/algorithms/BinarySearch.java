package ru.gorbunov.test.algorithms;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

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
