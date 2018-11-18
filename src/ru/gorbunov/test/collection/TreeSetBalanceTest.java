package ru.gorbunov.test.collection;

import java.lang.reflect.Field;
import java.util.TreeSet;

public class TreeSetBalanceTest {

    public static void main(String[] args) {
        final TreeSet<Integer> set = new TreeSet<>();
        for (int i = 10; i > 0; i--) {
            set.add(i);
            printRootElement(set);
        }
        System.out.println(set);
    }

    private static void printRootElement(TreeSet<Integer> set) {
        try {
            final Field map = set.getClass().getDeclaredField("m");
            map.setAccessible(true);
            final Object mapValue = map.get(set);

            final Field root = mapValue.getClass().getDeclaredField("root");
            root.setAccessible(true);
            final Object o = root.get(mapValue);

            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
