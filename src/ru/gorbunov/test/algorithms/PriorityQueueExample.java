package ru.gorbunov.test.algorithms;

import java.io.ByteArrayInputStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityQueueExample {
    public static void main(String[] args) {
        new PriorityQueueExample().process();
    }

    private void process() {
        Scanner scanner = new Scanner(new ByteArrayInputStream(("8\n" +
                "Insert 200\n" +
                "Insert 10\n" +
                "Insert 5\n" +
                "Insert 500\n" +
                "ExtractMax\n" +
                "ExtractMax\n" +
                "ExtractMax\n" +
                "ExtractMax").getBytes()));
        //Scanner scanner = new Scanner(System.in);
        final int commandCount = scanner.nextInt();
        final PriorityQueue<Integer> queue = new PriorityQueue<>((Comparator<Integer>) Comparator.naturalOrder().reversed());
        for (int i = 0; i < commandCount; i++) {
            final String command = scanner.next();
            if ("Insert".equals(command)) {
                queue.add(scanner.nextInt());
            } else {
                System.out.println(queue.poll());
            }
        }
    }
}
