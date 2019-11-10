package ru.gorbunov.test.algorithms.structures;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {
        final InputStream inputStream = new ByteArrayInputStream(("12\n" +
                "add 911 police\n" +
                "add 76213 Mom\n" +
                "add 17239 Bob\n" +
                "find 76213\n" +
                "find 910\n" +
                "find 911\n" +
                "del 910\n" +
                "del 911\n" +
                "find 911\n" +
                "find 76213\n" +
                "add 76213 daddy\n" +
                "find 76213").getBytes());
        new PhoneBook().process(inputStream);
    }

    private void process(InputStream inputStream) {
        final Scanner scanner = new Scanner(inputStream);
        final int commandCount = Integer.valueOf(scanner.nextLine());
        final HashMap<String, String> phoneBook = new HashMap<>();
        for (int i = 0; i < commandCount; i++) {
            final String command = scanner.nextLine();
            if (command.startsWith("add")) {
                final int startOfName = command.indexOf(' ', 4);
                final String number = command.substring(4, startOfName);
                final String name = command.substring(startOfName + 1);
                phoneBook.put(number, name);
            } else if (command.startsWith("del")) {
                final String number = command.substring(4);
                phoneBook.remove(number);
            } else {
                final String number = command.substring(5);
                System.out.println(phoneBook.getOrDefault(number, "not found"));
            }
        }
    }
}
