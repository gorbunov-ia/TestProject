package ru.gorbunov.test.algorithms.methods;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * По данным двум числам 1≤a,b≤2⋅109 найдите их наибольший общий делитель (НОД).
 */
public class GreatestCommonDivisor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new ByteArrayInputStream("14159572 63967072".getBytes()));
        int a = scanner.nextInt();
        System.out.println(a);
        int b = scanner.nextInt();
        System.out.println(b);
        System.out.println(gcd(a, b));

    }

    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (a > b) {
            return gcd(a % b, b);
        }
        return gcd(a, b % a);
    }
}
