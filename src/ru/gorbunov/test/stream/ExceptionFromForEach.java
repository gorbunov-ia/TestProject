package ru.gorbunov.test.stream;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExceptionFromForEach {

    public static void main(String[] args) {
        System.out.println("start");

//        fromIterable(createTestData());
//        fromStream(createTestData());
        fromParallelStream(createTestData());

        System.out.println("finish");
    }

    private static void fromIterable(Collection<Integer> source) {
        source.forEach(ExceptionFromForEach::checkDigit);
    }

    private static void fromStream(Collection<Integer> source) {
        source.stream()
                .forEach(ExceptionFromForEach::checkDigit);
    }

    private static void fromParallelStream(Collection<Integer> source) {
        source.parallelStream()
                .forEach(ExceptionFromForEach::checkDigit);
    }

    private static Collection<Integer> createTestData() {
        return Stream.iterate(0, i -> ++i)
                .limit(100_000)
                .collect(Collectors.toList());
    }

    private static void checkDigit(Integer digit) {
        if (digit.equals(90_000)) {
            throw new RuntimeException("Limit");
        }
    }
}
