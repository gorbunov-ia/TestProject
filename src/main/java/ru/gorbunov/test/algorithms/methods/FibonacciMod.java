package ru.gorbunov.test.algorithms.methods;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Даны целые числа 1≤n≤10^18 и 2≤m≤10^5, необходимо найти остаток от деления n-го числа Фибоначчи на m.
 */
public class FibonacciMod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new ByteArrayInputStream("1000 100".getBytes()));
//        Scanner scanner = new Scanner(new ByteArrayInputStream("10 2".getBytes()));
        //Scanner scanner = new Scanner(System.in);

        final long fibNumber = scanner.nextLong();
        final int modValue = scanner.nextInt();

        int[] restValues = new int[6 * modValue + 3];
        restValues[0] = 0;
        restValues[1] = 1;

        int currentFibNumber = 1;

        while (currentFibNumber < fibNumber) {
            currentFibNumber++;
            restValues[currentFibNumber] = (restValues[currentFibNumber - 2] + restValues[currentFibNumber - 1]) % modValue;

            if (restValues[currentFibNumber - 1] == 0 && restValues[currentFibNumber] == 1) {
                System.out.println(restValues[(int) (fibNumber % (currentFibNumber - 1))]);
                return;
            }
        }
        System.out.println(restValues[currentFibNumber]);
    }

    void withBigInteger(long fibNumber, int originalModValue) {
        final BigInteger modValue = BigInteger.valueOf(originalModValue);

        BigInteger prevSequenceValue = BigInteger.ZERO;
        BigInteger currentSequenceValue = BigInteger.ONE;
        long currentFibNumber = 1;

        List<Integer> period = new ArrayList<>(modValue.intValue() * 6);
        period.add(prevSequenceValue.intValue());
        period.add(currentSequenceValue.intValue());

        int prevModValue;
        int currentModValue = 1;

        while (currentFibNumber < fibNumber) {
            final BigInteger tmp = prevSequenceValue.add(currentSequenceValue);
            prevSequenceValue = currentSequenceValue;
            currentSequenceValue = tmp;
            currentFibNumber++;

            prevModValue = currentModValue;
            currentModValue = currentSequenceValue.mod(modValue).intValue();

            period.add(currentModValue);
            if (prevModValue == 0 && currentModValue == 1) {
                System.out.println(period.get((int)(fibNumber % (period.size() - 2))));
                return;
            }
        }
        System.out.println(currentModValue);
    }

}
