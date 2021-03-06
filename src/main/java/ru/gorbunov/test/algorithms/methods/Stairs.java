package ru.gorbunov.test.algorithms.methods;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Даны число 1≤n≤102 ступенек лестницы и целые числа −10^4≤a_1,…,a_n≤10^4, которыми помечены ступеньки.
 * Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх (от нулевой до n-й ступеньки),
 * каждый раз поднимаясь на одну или две ступеньки.
 */
public class Stairs {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(new ByteArrayInputStream("3\n-1 2 1".getBytes()));
//        Scanner scanner = new Scanner(new ByteArrayInputStream("2\n1 2".getBytes()));
//        Scanner scanner = new Scanner(new ByteArrayInputStream("5\n-64 -16 -13 -9 -48".getBytes()));
        Scanner scanner = new Scanner(new ByteArrayInputStream("8\n3 4 10 10 0 -6 -10 0".getBytes()));
//        Scanner scanner = new Scanner(System.in);
        final int stairsCount = scanner.nextInt();
        final int[] stairsWeights = new int[stairsCount];
        for (int i = 0; i < stairsCount; i++) {
            stairsWeights[i] = scanner.nextInt();
        }

        if (stairsCount == 1) {
            System.out.println(stairsWeights[0]);
        }

        int result = stairsWeights[0];
        int previousResult = 0;
        for (int i = 1; i < stairsCount - 1; i++) {
            if (result + stairsWeights[i] > previousResult + stairsWeights[i]) {
                previousResult = result;
                result += stairsWeights[i];
            } else {
                int temp = result;
                result = previousResult + stairsWeights[i];
                previousResult = temp;
            }
        }
        System.out.println(Math.max(result, previousResult) + stairsWeights[stairsCount - 1]);
    }
}
