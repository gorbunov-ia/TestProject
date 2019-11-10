package ru.gorbunov.test.algorithms.methods;

/**
 * Найти наибольший общий делитель (НОД) нескольких чисел.
 */
public class GreatestCommonDivisorMultiple {

    public static void main(String[] args) {
        System.out.println(gcd(new int[]{12, 24, 78, 294, 570, 36}));
    }

    private static int gcd(int[] digits) {
        if (digits == null || digits.length < 2) {
            throw new IllegalArgumentException();
        }
        int result = digits[0];
        for(int i = 1; i < digits.length; i++) {
            result = GreatestCommonDivisor.gcd(result, digits[i]);
        }
        return result;
    }

}
