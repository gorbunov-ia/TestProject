package ru.gorbunov.test.algorithms;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * В первой строке задано два целых числа 1≤n≤50000 и 1≤m≤50000 — количество отрезков и точек на прямой, соответственно.
 * Следующие n строк содержат по два целых числа a_i и b_i (a_i≤b_i) — координаты концов отрезков.
 * Последняя строка содержит m целых чисел — координаты точек. Все координаты не превышают 10^8 по модулю.
 * Точка считается принадлежащей отрезку, если она находится внутри него или на границе.
 * Для каждой точки в порядке появления во вводе выведите, скольким отрезкам она принадлежит.
 */
public class LinesContainsPoints {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(new ByteArrayInputStream((
//                "2 3\n" +
//                "0 5\n" +
//                "7 10\n" +
//                "1 6 11").getBytes()));
        Scanner scanner = new Scanner((new ByteArrayInputStream(("6 6\n" +
                "0 3\n" +
                "1 3\n" +
                "2 3\n" +
                "3 4\n" +
                "3 5\n" +
                "3 6\n" +
                "1 2 3 4 5 6").getBytes())));
        //Scanner scanner = new Scanner(System.in);

        final int lineCount = scanner.nextInt();
        final int pointCount = scanner.nextInt();

        final LineDetector detector = new LineDetector(lineCount);
        for (int i = 0; i < lineCount; i++) {
            detector.addLine(scanner.nextInt(), scanner.nextInt());
        }
        detector.prepare();

        final StringBuilder stringBuilder = new StringBuilder(pointCount);
        stringBuilder.append(detector.detect(scanner.nextInt()));
        for (int i = 1; i < pointCount; i++) {
            stringBuilder.append(" ").append(detector.detect(scanner.nextInt()));
        }
        System.out.print(stringBuilder.toString());
    }

    private static class LineDetector {

        private int[] start;
        private int[] end;
        private int currentLine = 0;

        LineDetector(int lineCount) {
            this.start = new int[lineCount];
            this.end = new int[lineCount];
        }

        void addLine(int start, int end) {
            this.start[currentLine] = start;
            this.end[currentLine] = end;
            currentLine++;
        }

        void prepare() {
            Arrays.sort(start);
            Arrays.sort(end);
        }

        int detect(int point) {
            /*
            int linesEnd = 0;
            for (int i = 0; i < end.length; i++) {
                if (end[i] >= point) {
                    break;
                }
                linesEnd++;
            }
            int linesStart = 0;
            for (int i = start.length - 1; i >= 0; i--) {
                if (start[i] <= point) {
                    break;
                }
                linesStart++;
            }
            return currentLine - linesStart - linesEnd;
            */
            int linesStart = binarySearch(start, point, true);
            int linesEnd = binarySearch(end, point, false);
            return linesEnd - linesStart;
        }

        private int binarySearch(int[] source, int point, boolean include) {
            int l = -1;
            int r = source.length;
            while (r > l + 1) {
                int m = (int) (Math.ceil((double) l + ((r - l) / 2)));
                if (include) {
                    if (source[m] > point) {
                        r = m;
                    } else {
                        l = m;
                    }
                } else {
                    if (source[m] >= point) {
                        r = m;
                    } else {
                        l = m;
                    }
                }
            }
            return source.length - r;
        }

    }
}
