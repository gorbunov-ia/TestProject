package ru.gorbunov.test.algorithms.structures;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Хеширование цепочками — один из наи-
 * более популярных методов реализации
 * хеш-таблиц на практике.
 * При добавлении строки в цепочку, строка должна
 * добавляться в начало цепочки.
 */
public class MyHashSet {

    private static final String ADD = "add ";
    private static final String DEL = "del ";
    private static final String FIND = "find ";
    private static final String CHECK = "check ";

    private static final int cacheFunctionX = 263;
    private static final int cacheFunctionAbs = 1_000_000_007;
    private static final long[] cacheFunctionSeeds;

    static {
        final long[] seeds = new long[15];
        seeds[0] = 1;
        seeds[1] = cacheFunctionX;
        for (int i = 2; i < seeds.length; i++) {
            seeds[i] = ((seeds[i - 1] % cacheFunctionAbs) * cacheFunctionX) % cacheFunctionAbs;
        }
        cacheFunctionSeeds = seeds;
    }

    private final LinkedList<String>[] map;

    public MyHashSet(int capacity) {
        map = (LinkedList<String>[]) Array.newInstance(LinkedList.class, capacity);
    }

    public static void main(String[] args) {
        final InputStream input = createInput();
        final Scanner scanner = new Scanner(input);
        int tableSize = Integer.valueOf(scanner.nextLine());
        int commandCount = Integer.valueOf(scanner.nextLine());
        final MyHashSet myHashSet = new MyHashSet(tableSize);
        for (int i = 0; i < commandCount; i++) {
            final String command = scanner.nextLine();
            if (command.startsWith(ADD)) {
                myHashSet.add(command.substring(ADD.length()));
            } else if (command.startsWith(DEL)) {
                myHashSet.del(command.substring(DEL.length()));
            } else if (command.startsWith(FIND)) {
                System.out.println(myHashSet.find(command.substring(FIND.length())));
            } else if (command.startsWith(CHECK)) {
                int chainNumber = Integer.valueOf(command.substring(CHECK.length()));
                System.out.println(myHashSet.check(chainNumber));
            } else {
                throw new IllegalArgumentException();
            }

        }
    }

    void add(String element) {
        int hash = calculateHash(element);
        final LinkedList<String> list = getListByIndex(hash);
        if (!list.contains(element)) {
            list.addFirst(element);
        }
    }

    private int calculateHash(String element) {
        long result = 0;
        for (int charNumber = 0; charNumber < element.length(); charNumber++) {
            int ascii = element.charAt(charNumber);
            result = result + ascii * cacheFunctionSeeds[charNumber];
        }
        return (int) (result % cacheFunctionAbs % map.length);
    }

    void del(String element) {
        int hash = calculateHash(element);
        getListByIndex(hash).remove(element);
    }

    String find(String element) {
        int hash = calculateHash(element);
        if (getListByIndex(hash).contains(element)) {
            return "yes";
        }
        return "no";
    }

    String check(int i) {
        final LinkedList<String> list = getListByIndex(i);
        return list.stream().collect(Collectors.joining(" "));
    }

    private LinkedList<String> getListByIndex(int index) {
        LinkedList<String> result = map[index];
        if (result != null) {
            return result;
        }
        result = new LinkedList<>();
        map[index] = result;
        return result;
    }

    private static InputStream createInput() {
        return new ByteArrayInputStream(("5\n" +
                "12\n" +
                "add world\n" +
                "add HellO\n" +
                "check 4\n" +
                "find World\n" +
                "find world\n" +
                "del world\n" +
                "check 4\n" +
                "del HellO\n" +
                "add luck\n" +
                "add GooD\n" +
                "check 2\n" +
                "del good").getBytes());
    }

}
