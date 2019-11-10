package ru.gorbunov.test.algorithms.methods;

import java.io.ByteArrayInputStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Первая строка входа содержит число операций 1≤n≤105. Каждая из последующих n строк задают операцию одного
 * из следующих двух типов:
 * <p>
 * 𝙸𝚗𝚜𝚎𝚛𝚝 𝚡, где 0≤x≤10^9 — целое число;
 * 𝙴𝚡𝚝𝚛𝚊𝚌𝚝𝙼𝚊𝚡.
 * <p>
 * Первая операция добавляет число x в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.
 */
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
        final PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.<Integer>naturalOrder().reversed());
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
