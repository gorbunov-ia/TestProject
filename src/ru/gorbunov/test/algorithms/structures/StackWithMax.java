package ru.gorbunov.test.algorithms.structures;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Стек с поддержкой максимума.
 */
public class StackWithMax {
    private static final LinkedList<Integer> stack = new LinkedList<>();
    private static final LinkedList<Integer> maxElement = new LinkedList<>();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(new ByteArrayInputStream(("5\n" +
                "push 1\n" +
                "push 2\n" +
                "max\n" +
                "pop\n" +
                "max").getBytes()));
        final int operationCount = Integer.parseInt(scanner.nextLine());
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < operationCount; i++) {
            final String command = scanner.nextLine();
            if (command.startsWith("push")) {
                final int value = Integer.parseInt(command.substring(5));
                stack.push(value);
                if (maxElement.isEmpty()) {
                    maxElement.push(value);
                } else {
                    maxElement.push(Math.max(maxElement.peek(), value));
                }
            } else if ("pop".equals(command)) {
                stack.pop();
                maxElement.pop();
            } else {
                builder.append(maxElement.peek()).append("\n");
            }
        }
        System.out.println(builder.toString());
    }
}
