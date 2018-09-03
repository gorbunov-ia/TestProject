package ru.gorbunov.test.algorithms.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * По данным n отрезкам необходимо найти множество точек минимального размера,
 * для которого каждый из отрезков содержит хотя бы одну из точек.
 * <p>
 * В первой строке дано число 1≤n≤100 отрезков. Каждая из последующих n строк содержит по два числа 0≤l≤r≤109,
 * задающих начало и конец отрезка. Выведите оптимальное число m точек и сами m точек.
 * Если таких множеств точек несколько, выведите любое из них.
 */
public class OptimalPointsInLines {

    public static void main(String[] args) {
        new OptimalPointsInLines().process();
    }

    private void process() {

        final List<Line> lines = createLines();

        lines.sort(null);

        //lines.forEach(System.out::println);

        final List<Integer> points = getPoints(lines);
        System.out.println(points.size());
        System.out.print(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            System.out.print(" " + points.get(i));
        }
    }

    private List<Line> createLines() {
        //Scanner scanner = new Scanner(new ByteArrayInputStream("4\n1 3\n2 5\n5 6\n4 7\n".getBytes()));
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        List<Line> lines = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            lines.add(new Line(scanner.nextInt(), scanner.nextInt()));
        }
        return lines;
    }

    private List<Integer> getPoints(List<Line> lines) {
        List<Integer> resultPoints = new ArrayList<>();
        if (lines.isEmpty()) {
            return resultPoints;
        }
        for (Line line : lines) {
            if (!resultPoints.isEmpty() && line.contains(resultPoints.get(resultPoints.size() - 1))) {
                continue;
            }
            resultPoints.add(line.end);
        }
        return resultPoints;
    }

    private class Line implements Comparable<Line> {
        private final int start;
        private final int end;

        Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + ']';
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(end, o.end);
        }

        boolean contains(int point) {
            return start <= point && end >= point;
        }

    }

}
