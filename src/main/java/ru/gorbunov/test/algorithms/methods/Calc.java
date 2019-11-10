package ru.gorbunov.test.algorithms.methods;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * У вас есть примитивный калькулятор, который умеет выполнять всего три операции с текущим числом x:
 * заменить x на 2x, 3x или x+1. По данному целому числу 1≤n≤10^5 определите минимальное число операций k,
 * необходимое, чтобы получить n из 1. Выведите k и последовательность промежуточных чисел.
 */
public class Calc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new ByteArrayInputStream("96234".getBytes()));
//        Scanner scanner = new Scanner(new ByteArrayInputStream("10".getBytes()));
//        Scanner scanner = new Scanner(System.in);
        final int targetValue = scanner.nextInt();

        if (1 == targetValue) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        final int[] digits = new int[targetValue];
        for (int i = 1; i < targetValue; i++) {
            int currentDigit = i + 1;
            int div3OprtCount = currentDigit % 3 == 0 ? digits[currentDigit / 3 - 1] + 1 : Integer.MAX_VALUE;
            int div2OprtCount = currentDigit % 2 == 0 ? digits[currentDigit / 2 - 1] + 1 : Integer.MAX_VALUE;
            digits[i] = Math.min(Math.min(div3OprtCount, div2OprtCount), digits[i - 1] + 1);
        }

        System.out.println(digits[digits.length - 1]);
        List<Integer> resultDigits = new ArrayList<>(digits[digits.length - 1] + 1);
        resultDigits.add(targetValue);

        int currentValue = targetValue;
        int operationCount = digits[digits.length - 1];
        while (operationCount != 0) {
            if (digits[currentValue - 1 - 1] == operationCount - 1) {
                operationCount--;
                currentValue--;
                resultDigits.add(currentValue);
                continue;
            }
            if (currentValue % 3 == 0 && digits[currentValue / 3 - 1] == operationCount - 1) {
                operationCount--;
                currentValue = currentValue / 3;
                resultDigits.add(currentValue);
                continue;
            }
            if (currentValue % 2 == 0 && digits[currentValue / 2 - 1] == operationCount - 1) {
                operationCount--;
                currentValue = currentValue / 2;
                resultDigits.add(currentValue);
            }
        }
        final StringBuilder stringBuilder = new StringBuilder(resultDigits.size() * 2);
        stringBuilder.append(resultDigits.get(resultDigits.size() - 1));
        for (int i = resultDigits.size() - 2; i >= 0; i--) {
            stringBuilder.append(" ").append(resultDigits.get(i));
        }
        System.out.println(stringBuilder.toString());
    }
}
