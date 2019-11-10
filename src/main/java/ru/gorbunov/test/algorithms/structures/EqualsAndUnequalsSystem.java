package ru.gorbunov.test.algorithms.structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Система равенств и неравенств.
 * Проверить, можно ли присвоить переменным целые значения, чтобы
 * выполнить заданные равенства вида x_i = x_j и неравенства вида x_p != x_q.
 * Формат входа. Первая строка содержит числа n, e, d. Каждая из сле-
 * дующих e строк содержит два числа i и j и задаёт равенство
 * x_i = x_j. Каждая из следующих d строк содержит два числа i и j и
 * задаёт неравенство x_i != x_j. Переменные индексируются с 1: x_1,...,x_n.
 * Формат выхода. Выведите 1, если переменным x_1,...,x_n можно
 * присвоить целые значения, чтобы все равенства и неравенства
 * выполнились. В противном случае выведите 0.
 */
public class EqualsAndUnequalsSystem {
    private int[] parents;

    public static void main(String[] args) {
        System.out.println(new EqualsAndUnequalsSystem().process(System.in));
    }

    private int process(InputStream in) {
        final Scanner scanner = new Scanner(in);
        int variableCount = scanner.nextInt();
        int equalsCount = scanner.nextInt();
        int unequalsCount = scanner.nextInt();
        parents = new int[variableCount];
        for (int i = 1; i <= variableCount; i++) {
            makeSet(i);
        }
        for (int i = 0; i < equalsCount; i++) {
            final int xi = scanner.nextInt();
            final int xj = scanner.nextInt();
            union(xi, xj);
        }
        for (int i = 0; i < unequalsCount; i++) {
            final int xi = scanner.nextInt();
            final int xj = scanner.nextInt();
            if (find(xi) == find(xj)) {
                return 0;
            }
        }
        return 1;
    }

    private void makeSet(int variable) {
        parents[variable - 1] = variable - 1;
    }

    private int find(int variable) {
        variable = variable - 1;
        if (variable != parents[variable]) {
            parents[variable] = find(parents[variable] + 1) - 1;
        }
        return parents[variable] + 1;
    }

    private void setParent(int variable, int parent) {
        parents[variable - 1] = parent - 1;
    }

    private void union(int xi, int xj) {
        final int parentXi = find(xi);
        final int parentXj = find(xj);
        setParent(parentXi, parentXj);
    }

    @ParameterizedTest
    @MethodSource("createTestData")
    public void test(InputStream inputStream, Integer result) {
        Assertions.assertEquals(result.intValue(), new EqualsAndUnequalsSystem().process(inputStream));
    }

    private static Stream<Arguments> createTestData() {
        return Stream.of(
                Arguments.of(new ByteArrayInputStream(("4 6 0\n" +
                        "1 2\n" +
                        "1 3\n" +
                        "1 4\n" +
                        "2 3\n" +
                        "2 4\n" +
                        "3 4\n").getBytes()), 1),
                Arguments.of(new ByteArrayInputStream(("4 6 1\n" +
                        "1 2\n" +
                        "1 3\n" +
                        "1 4\n" +
                        "2 3\n" +
                        "2 4\n" +
                        "3 4\n" +
                        "1 2").getBytes()), 0),
                Arguments.of(new ByteArrayInputStream(("6 5 3\n" +
                        "2 3\n" +
                        "1 5\n" +
                        "2 5\n" +
                        "3 4\n" +
                        "4 2\n" +
                        "6 1\n" +
                        "4 6\n" +
                        "4 5").getBytes()), 0));
    }

}
