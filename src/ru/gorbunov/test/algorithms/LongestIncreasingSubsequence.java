package ru.gorbunov.test.algorithms;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] source = getSource();
        int[] sequences = new int[source.length];

        for (int i = 0; i < sequences.length; i++) {
            sequences[i] = 1;
            for (int j = 0; j < i; j++) {
                // Increasing
                if (source[j] < source[i] && sequences[j] + 1 > sequences[i]) {
                    sequences[i] = sequences[j] + 1;
                }
                // Dividing
//                if (source[i] % source[j] == 0 && sequences[j] + 1 > sequences[i]) {
//                    sequences[i] = sequences[j] + 1;
            }
        }
        System.out.println(Arrays.stream(sequences).max().orElse(0));
    }

    private static int[] getSource() {
        return new int[]{7, 2, 1, 3, 8, 4, 9, 1, 2, 6, 5, 9, 3, 8, 1};
//        return new int[]{3, 6, 7, 12};
//        return new int[]{7, 6, 1, 6, 4, 1, 2, 4, 10, 1};
    }

}
