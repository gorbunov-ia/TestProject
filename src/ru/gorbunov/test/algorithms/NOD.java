package ru.gorbunov.test.algorithms;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * По данным двум числам 1≤a,b≤2⋅109 найдите их наибольший общий делитель.
 */
public class NOD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new ByteArrayInputStream("14159572 63967072".getBytes()));
        int a = scanner.nextInt();
        System.out.println(a);
        int b = scanner.nextInt();
        System.out.println(b);
        System.out.println(nod(a, b));

    }

    private static int nod(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (a > b) {
            return nod(a % b, b);
        }
        return nod(a, b % a);
    }
}
