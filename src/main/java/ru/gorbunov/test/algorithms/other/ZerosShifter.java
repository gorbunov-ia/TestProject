package ru.gorbunov.test.algorithms.other;

import java.util.Arrays;

public class ZerosShifter {

    public static void main(String[] args) {
        int[] data = {8, 9, 0, 3, 0, 0, 5};
        new ZerosShifter().shiftZeroes(data);
        System.out.println(Arrays.toString(data));
    }

    void shiftZeroes(int[] a) {
        if (a == null) {
            throw new NullPointerException("Array should not be null");
        }

        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                if (i != j) { //optimization. Array without zeros [1, 1, 1]
                    a[j] = a[i];
                }
                j++;
            }
        }

        if (j > 0) { //optimization. Array with all zeros [0, 0, 0]
            for (; j < a.length; j++) {
                a[j] = 0;
            }
        }
    }

}
