package ru.gorbunov.test.trycatch;

import java.util.Arrays;

public class Example {
    public static void main(String[] args) {
        new Example().run();
    }

    void run() {
        try {
//            example1();
            example2();
        } catch (Exception e) {
            System.out.println("------");
            printException(e);
        }
    }

    private void example1() {
        try {
            throw new RuntimeException("First");
        } catch (Exception e) {
            throw new RuntimeException("Second");
        } finally {
            throw new RuntimeException("Third");
        }
    }

    private void example2() {
        try (AutoCloseable clazz = get()) {
            System.out.println("Class: " + clazz);
            throw new RuntimeException("First");
        } catch (Exception e) {
            printException(e);
            throw new RuntimeException("Second", e);
        } finally {
            throw new RuntimeException("Third");
        }
    }

    private AutoCloseable get() {
        if (1 == 0) {
            throw new RuntimeException("Zero");
        }
        return () -> {
            System.out.println("Close");
            throw new RuntimeException("Zero");
        };
    }

    private void printException(Exception e) {
        System.out.println("Msg: " + e.getMessage());
        System.out.println("Suppressed: " + Arrays.toString(e.getSuppressed()));
    }
}
