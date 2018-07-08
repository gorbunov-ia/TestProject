package ru.gorbunov.test.algorithms;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class BackpackDynamic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new ByteArrayInputStream("10 3\n1 4 8".getBytes()));
//        Scanner scanner = new Scanner(new ByteArrayInputStream("10 4\n30 6 14 3 16 4 9 2".getBytes()));
//        Scanner scanner = new Scanner(System.in);
        final int weight = scanner.nextInt();
        final int itemCount = scanner.nextInt();

        final int[][] items = new int[itemCount][2];
        for (int i = 0; i < itemCount; i++) {
            items[i][0] = scanner.nextInt(); // price
//            items[i][1] = scanner.nextInt(); // size
            items[i][1] = items[i][0];
        }

        final int[][] weightMatrix = new int[itemCount + 1][weight + 1];

        weightMatrix[0][0] = 0;
        for (int i = 1; i < weightMatrix.length; i++) {
            weightMatrix[i][0] = 0;
        }
        for (int i = 1; i < weightMatrix[0].length; i++) {
            weightMatrix[0][i] = 0;
        }

        for (int i = 1; i < weightMatrix.length; i++) {
            for (int w = 1; w < weightMatrix[0].length; w++) {
                weightMatrix[i][w] = weightMatrix[i - 1][w];
                if (items[i - 1][1] <= w) {
                    weightMatrix[i][w] = Math.max(weightMatrix[i][w],
                            weightMatrix[i - 1][w - items[i - 1][1]] + items[i - 1][0]);
                }
            }
        }
        System.out.println(weightMatrix[weightMatrix.length - 1][weightMatrix[0].length - 1]);
    }


}
