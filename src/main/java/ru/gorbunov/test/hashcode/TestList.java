package ru.gorbunov.test.hashcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class TestList {
    public static void main(String[] args) {
//        List<Integer> l1 = new ArrayList<>();
//        l1.add(10);
//        l1.add(20);
//        List<Integer> l2 = new ArrayList<>();
//        l2.add(20);
//        l2.add(10);
//        System.out.println(l1.hashCode() + " " + l2.hashCode());
//        System.out.println(l1.equals(l2));
        SortedSet<Integer> hs = new TreeSet<>();
        hs.add(10);
        hs.add(20);
        SortedSet<Integer> hs2 = new TreeSet<>();
        hs2.add(20);
        hs2.add(10);
        System.out.println(hs.equals(hs2));
    }
}
