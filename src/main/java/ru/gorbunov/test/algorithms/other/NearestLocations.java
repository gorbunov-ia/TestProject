package ru.gorbunov.test.algorithms.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Поиск N ближайших локаций к обьекту.
 */
public class NearestLocations {
    public static void main(String[] args) {
        new NearestLocations().process();
    }

    private void process() {
        final List<List<Integer>> points = new ArrayList<>();
        points.add(point(1,-1));
        points.add(point(1,2));
        points.add(point(3,4));
        final List<List<Integer>> lists = nearestLocations(points, 2);
        System.out.println(lists);
    }

    private List<Integer> point(int x, int y) {
        return Arrays.asList(x, y);
    }

    /**
     * @param allLocations список координат локаций <x, y>
     * @param topCount необходимое кол-во локаций в результате
     * @return топ локаций
     */
    private List<List<Integer>> nearestLocations(List<List<Integer>> allLocations, int topCount) {
        return allLocations.stream()
                .map(Point::new)
                .sorted()
                .limit(topCount)
                .map(Point::getSource)
                .collect(Collectors.toList());
    }

    private static class Point implements Comparable<Point>{
        private final int x;
        private final int y;

        private final long distance;
        private final List<Integer> source;

        private Point(List<Integer> point) {
            this.x = point.get(0);
            this.y = point.get(1);
            this.distance = x * x + y * y;
            this.source = point;
        }

        private List<Integer> getSource() {
            return source;
        }

        @Override
        public int compareTo(Point o) {
            return Long.compare(distance, o.distance);
        }
    }
}
