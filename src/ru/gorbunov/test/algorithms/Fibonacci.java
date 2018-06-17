package ru.gorbunov.test.algorithms;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        //InputStream inputStream = new ByteArrayInputStream("20".getBytes());
        //InputStream inputStream = System.in;
        //int number = new Integer(new Character((char) inputStream.read()).toString());
        Scanner scanner = new Scanner(new ByteArrayInputStream("875577".getBytes()));
        int number = scanner.nextInt();
        if (number <= 1) {
            System.out.println(number);
            return;
        }
        int[] array = new int[number];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2 ; i < number; i++) {
            array[i] = (array[i-2] + array[i-1]) % 10;
        }
        System.out.println(array[number-1]);
    }
}