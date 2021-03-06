package ru.gorbunov.test.algorithms.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * По данному числу 1≤n≤109 найдите максимальное число k, для которого n можно представить как сумму k различных
 * натуральных слагаемых. Выведите в первой строке число k, во второй — k слагаемых.
 */
public class DifferentTerms {

    public static void main(String[] args) {
        new DifferentTerms().process();
    }

    private void process() {
        Scanner scanner = new Scanner(System.in);
        final int goal = scanner.nextInt();
        printDifferentTerms(calcDifferentTerms(goal));
    }

    private void printDifferentTerms(List<Integer> differentTerms) {
        System.out.println(differentTerms.size());
        System.out.print(differentTerms.get(0));
        for (int i = 1; i < differentTerms.size(); i++) {
            System.out.print(" " + differentTerms.get(i));
        }
    }

    private List<Integer> calcDifferentTerms(int goal) {
        final List<Integer> result = new ArrayList<>();
        int i = 1;
        while (goal > 2 * i ) {
            goal -= i;
            result.add(i++);
        }
        result.add(goal);
        return result;
    }

}
