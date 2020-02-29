package ru.gorbunov.test.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PascalTriangle {

    public static void main(String[] args) {
        final PascalTriangle pascalTriangle = new PascalTriangle();
        for (int rows : Arrays.asList(1,2,5)) {
            System.out.println("---------------------");
            pascalTriangle.build(rows).forEach(System.out::println);
        }
    }

    private List<List<Integer>> build(int rows) {
        if (rows < 0) {
            throw new IllegalArgumentException("Row number should not be negative");
        }
        if (rows == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> triangle = new ArrayList<>(rows);
        triangle.add(Collections.singletonList(1));
        for (int currentRowNumber = 1; currentRowNumber < rows; currentRowNumber++) {
            List<Integer> currentRow = new ArrayList<>();
            currentRow.add(1);

            for (int columnNumber = 1; columnNumber < currentRowNumber; columnNumber++) {
                List<Integer> previousRow = triangle.get(currentRowNumber - 1);
                currentRow.add(previousRow.get(columnNumber - 1) + previousRow.get(columnNumber));
            }

            currentRow.add(1);
            triangle.add(currentRow);
        }
        return triangle;
    }
}
