package ru.gorbunov.test.algorithms.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A* search algorithm
 */
public class PathSearching {

    private static final int TARGET = 9;
    private static final int ROUTE = 1;
    private static final int NOT_FOUND = -1;

    public static void main(String[] args) {
        new PathSearching().process();
    }

    private void process() {
        final List<List<Integer>> area = new ArrayList<>();
        area.add(Arrays.asList(1, 0, 9));
        area.add(Arrays.asList(1, 0, 1));
        area.add(Arrays.asList(1, 1, 1));

        System.out.println(new Area(area).getRouteLength());
    }

    private static final class Point {
        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

    }

    private static final class Area {

        private final Point start = new Point(0, 0);
        private final Point target;
        private final Set<Point> points;

        private Integer routeLength;

        private Area(List<List<Integer>> area) {
            final Map<Integer, Set<Point>> pointsMap = createPoints(area);
            target = Optional.ofNullable(pointsMap.get(TARGET))
                    .map(set -> set.iterator().next())
                    .orElseThrow(IllegalArgumentException::new);
            points = pointsMap.getOrDefault(ROUTE, new HashSet<>());
            points.add(target);
        }

        int getRouteLength() {
            if (routeLength == null) {
                routeLength = calculateRouteLength();
            }
            return routeLength;
        }

        private static Map<Integer, Set<Point>> createPoints(List<List<Integer>> area) {
            final Map<Integer, Set<Point>> result = new HashMap<>();
            for (int i = 0; i < area.size(); i++) {
                for (int j = 0; j < area.get(0).size(); j++) {
                    result.computeIfAbsent(area.get(i).get(j), k -> new HashSet<>()).add(new Point(j, i));
                }
            }
            return result;
        }

        private Point returnIfAvailable(Point point) {
            return points.contains(point) ? point : null;
        }

        private Point getLeft(Point point) {
            return returnIfAvailable(new Point(point.getX() - 1, point.getY()));
        }

        private Point getRight(Point point) {
            return returnIfAvailable(new Point(point.getX() + 1, point.getY()));
        }

        private Point getUp(Point point) {
            return returnIfAvailable(new Point(point.getX(), point.getY() - 1));
        }

        private Point getDown(Point point) {
            return returnIfAvailable(new Point(point.getX(), point.getY() + 1));
        }

        private Collection<Point> getNeighbors(Point current) {
            return Stream.of(getRight(current), getDown(current), getLeft(current), getUp(current))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        private Integer heuristicCostEstimate(Point from, Point to) {
            return Double.valueOf(Math.sqrt(Math.pow(from.getX() - to.getX(), 2)
                    + Math.pow(from.getY() - to.getY(), 2))).intValue();
        }

        private int calculateRouteLength() {
            final Map<Point, Integer> fScore = new HashMap<>();
            final Map<Point, Point> cameFrom = new HashMap<>();
            final Map<Point, Integer> gScore = new HashMap<>();

            final Set<Point> closed = new HashSet<>();
            final Set<Point> open = new TreeSet<>(new PointComparator(fScore));

            open.add(start);
            gScore.put(start, 0);
            fScore.put(start, heuristicCostEstimate(start, target));

            while (!open.isEmpty()) {
                Point current = open.iterator().next();

                if (current.equals(target)) {
                    return calculateLength(cameFrom, current);
                }

                open.remove(current);
                closed.add(current);

                for (Point neighbor : getNeighbors(current)) {
                    if (closed.contains(neighbor)) {
                        continue;
                    }

                    final Integer tentativeGScore = Optional.ofNullable(gScore.get(current))
                            .map(val -> val + 1)
                            .orElse(Integer.MAX_VALUE);

                    if (!open.contains(neighbor)) {
                        open.add(neighbor);
                    } else if (tentativeGScore >= gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        continue;
                    }

                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, gScore.get(neighbor) + heuristicCostEstimate(neighbor, target));
                }

            }

            return NOT_FOUND;
        }

        private int calculateLength(Map<Point, Point> cameFrom, Point from) {
            int length = 0;
            final List<Point> path = new ArrayList<>();
            path.add(from);
            Point current = from;
            while (cameFrom.containsKey(current)) {
                current = cameFrom.get(current);
                path.add(current);
                length++;
            }
            System.out.println(path);
            return length;
        }
    }

    private static class PointComparator implements Comparator<Point> {

        private final Map<Point, Integer> fScore;

        private PointComparator(Map<Point, Integer> fScore) {
            this.fScore = fScore;
        }

        @Override
        public int compare(Point o1, Point o2) {
            return fScore.computeIfAbsent(o2, k -> Integer.MAX_VALUE)
                    .compareTo(fScore.computeIfAbsent(o1, k -> Integer.MAX_VALUE));
        }
    }

}
