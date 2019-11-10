package ru.gorbunov.test.algorithms.structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Симуляция обработки сетевых пакетов.
 * <p>
 * Вход. Размер буфера size и число пакетов n, а так-
 * же две последовательности arrival_1,..,arrival_n и
 * duration_1,...,duration_n, обозначающих время поступ-
 * ления и длительность обработки n пакетов.
 * Выход. Для каждого из данных n пакетов необходимо
 * вывести время начала его обработки или −1, если пакет
 * не был обработан (это происходит в случае, когда пакет
 * поступает в момент, когда в буфере компьютера уже
 * находится size пакетов).
 */
public class NetworkPackageProcessor {
    private final Queue<NetworkPackage> queue = new LinkedList<>();

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int bufferSize = scanner.nextInt();
        final int packageCount = scanner.nextInt();
        final LinkedList<NetworkPackage> networkPackages = new LinkedList<>();
        for (int i = 0; i < packageCount; i++) {
            networkPackages.add(new NetworkPackage(i, scanner.nextInt(), scanner.nextInt()));
        }
        for (int pack : new NetworkPackageProcessor().process(networkPackages, bufferSize)) {
            System.out.println(pack);
        }
    }

    private List<Integer> process(Queue<NetworkPackage> networkPackages, int bufferSize) {
        if (networkPackages.isEmpty()) {
            return Collections.emptyList();
        }
        final ArrayList<NetworkPackageResult> result = new ArrayList<>(networkPackages.size());

        int timer = -1;
        out:
        while (true) {
            timer++;

            //read
            while (true) {
                final NetworkPackage networkPackage = networkPackages.peek();
                if (networkPackage != null && networkPackage.arrival == timer) {
                    networkPackages.remove();
                    if (queue.size() >= bufferSize) {
                        result.add(new NetworkPackageResult(networkPackage.number, -1));
                    } else if (queue.isEmpty() && networkPackage.duration == 0) {
                        result.add(new NetworkPackageResult(networkPackage.number, timer));
                    } else {
                        queue.add(networkPackage);
                    }
                } else {
                    break;
                }
            }

            //process
            boolean workExecuted = false;
            while (!workExecuted) {
                final NetworkPackage currentPackage = queue.peek();
                if (currentPackage == null) {
                    if (networkPackages.isEmpty()) {
                        break out;
                    } else {
                        continue out;
                    }
                }
                if (!currentPackage.decremented) {
                    result.add(new NetworkPackageResult(currentPackage.number, timer));
                }
                if (currentPackage.duration <= 1) {
                    queue.remove();
                } else {
                    currentPackage.decrementDuration();
                }
                workExecuted = (currentPackage.duration != 0);
            }
        }
        return result.stream()
                .sorted(Comparator.comparingInt(r -> r.number))
                .map(r -> r.startProcessTime)
                .collect(Collectors.toList());
    }

    private static class NetworkPackage {
        private final int number;
        private final int arrival;
        private int duration;
        private boolean decremented;

        private NetworkPackage(int number, int arrival, int duration) {
            this.number = number;
            this.arrival = arrival;
            this.duration = duration;
        }

        private void decrementDuration() {
            duration--;
            if (!decremented) {
                decremented = true;
            }
        }
    }

    private static class NetworkPackageResult {
        private final int number;
        private final int startProcessTime;

        private NetworkPackageResult(int number, int startProcessTime) {
            this.number = number;
            this.startProcessTime = startProcessTime;
        }

    }

    @ParameterizedTest
    @MethodSource("createTestData")
    public void test(List<NetworkPackage> networkPackages, int bufferSize, List<Integer> result) {
        Assertions.assertEquals(result, new NetworkPackageProcessor().process(new LinkedList<>(networkPackages), bufferSize));
    }

    private static Stream<Arguments> createTestData() {
        return Stream.of(
                Arguments.of(Collections.emptyList(), 1, Collections.emptyList()),
                Arguments.of(Collections.singletonList(new NetworkPackage(0, 0, 0)), 1, Collections.singletonList(0)),
                Arguments.of(Collections.singletonList(new NetworkPackage(0, 0, 1)), 1, Collections.singletonList(0)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 0, 1),
                        new NetworkPackage(1, 0, 1)
                ), 1, Arrays.asList(0, -1)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 0, 1),
                        new NetworkPackage(1, 1, 1)
                ), 1, Arrays.asList(0, 1)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 16, 0),
                        new NetworkPackage(1, 29, 3),
                        new NetworkPackage(2, 44, 6),
                        new NetworkPackage(3, 58, 0),
                        new NetworkPackage(4, 72, 2),
                        new NetworkPackage(5, 88, 8),
                        new NetworkPackage(6, 95, 7),
                        new NetworkPackage(7, 108, 6),
                        new NetworkPackage(8, 123, 9),
                        new NetworkPackage(9, 139, 6),
                        new NetworkPackage(10, 152, 6),
                        new NetworkPackage(11, 157, 3),
                        new NetworkPackage(12, 169, 3),
                        new NetworkPackage(13, 183, 1),
                        new NetworkPackage(14, 192, 0),
                        new NetworkPackage(15, 202, 8),
                        new NetworkPackage(16, 213, 8),
                        new NetworkPackage(17, 229, 3),
                        new NetworkPackage(18, 232, 3),
                        new NetworkPackage(19, 236, 3),
                        new NetworkPackage(20, 239, 4),
                        new NetworkPackage(21, 247, 8),
                        new NetworkPackage(22, 251, 2),
                        new NetworkPackage(23, 267, 7),
                        new NetworkPackage(24, 275, 7)
                ), 1, Arrays.asList(16, 29, 44, 58, 72, 88, -1, 108, 123, 139, 152, -1, 169, 183, 192, 202, 213, 229,
                        232, 236, 239, 247, -1, 267, 275)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 6, 23),
                        new NetworkPackage(1, 15, 44),
                        new NetworkPackage(2, 24, 28),
                        new NetworkPackage(3, 25, 15),
                        new NetworkPackage(4, 33, 7),
                        new NetworkPackage(5, 47, 41),
                        new NetworkPackage(6, 58, 25),
                        new NetworkPackage(7, 65, 5),
                        new NetworkPackage(8, 70, 14),
                        new NetworkPackage(9, 79, 8),
                        new NetworkPackage(10, 93, 43),
                        new NetworkPackage(11, 103, 11),
                        new NetworkPackage(12, 110, 25),
                        new NetworkPackage(13, 123, 27),
                        new NetworkPackage(14, 138, 40),
                        new NetworkPackage(15, 144, 19),
                        new NetworkPackage(16, 159, 2),
                        new NetworkPackage(17, 167, 23),
                        new NetworkPackage(18, 179, 43),
                        new NetworkPackage(19, 182, 31),
                        new NetworkPackage(20, 186, 7),
                        new NetworkPackage(21, 198, 16),
                        new NetworkPackage(22, 208, 41),
                        new NetworkPackage(23, 222, 23),
                        new NetworkPackage(24, 235, 26)
                ), 11, Arrays.asList(6, 29, 73, 101, 116, 123, 164, 189, 194, 208, 216, 259, 270, 295, 322, 362, -1,
                        381, -1, -1, -1, 404, 420, 461, 484)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 10, 37),
                        new NetworkPackage(1, 20, 45),
                        new NetworkPackage(2, 29, 24),
                        new NetworkPackage(3, 31, 17),
                        new NetworkPackage(4, 38, 43),
                        new NetworkPackage(5, 49, 30),
                        new NetworkPackage(6, 59, 12),
                        new NetworkPackage(7, 72, 28),
                        new NetworkPackage(8, 82, 45),
                        new NetworkPackage(9, 91, 10),
                        new NetworkPackage(10, 107, 46),
                        new NetworkPackage(11, 113, 4),
                        new NetworkPackage(12, 128, 16),
                        new NetworkPackage(13, 139, 1),
                        new NetworkPackage(14, 149, 41),
                        new NetworkPackage(15, 163, 0),
                        new NetworkPackage(16, 172, 22),
                        new NetworkPackage(17, 185, 1),
                        new NetworkPackage(18, 191, 17),
                        new NetworkPackage(19, 201, 3),
                        new NetworkPackage(20, 209, 11),
                        new NetworkPackage(21, 223, 30),
                        new NetworkPackage(22, 236, 17),
                        new NetworkPackage(23, 252, 42),
                        new NetworkPackage(24, 262, 0)
                ), 13, Arrays.asList(10, 47, 92, 116, 133, 176, 206, 218, 246, 291, 301, 347, 351, 367, 368, 409, 409,
                        431, -1, -1, 432, 443, -1, 473, -1)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 5, 11),
                        new NetworkPackage(1, 10, 14),
                        new NetworkPackage(2, 25, 17),
                        new NetworkPackage(3, 41, 22),
                        new NetworkPackage(4, 54, 36),
                        new NetworkPackage(5, 70, 13),
                        new NetworkPackage(6, 81, 8),
                        new NetworkPackage(7, 90, 12),
                        new NetworkPackage(8, 103, 21),
                        new NetworkPackage(9, 115, 38),
                        new NetworkPackage(10, 124, 18),
                        new NetworkPackage(11, 138, 15),
                        new NetworkPackage(12, 142, 13),
                        new NetworkPackage(13, 155, 31),
                        new NetworkPackage(14, 168, 0),
                        new NetworkPackage(15, 177, 49),
                        new NetworkPackage(16, 186, 8),
                        new NetworkPackage(17, 196, 30),
                        new NetworkPackage(18, 206, 37),
                        new NetworkPackage(19, 217, 49),
                        new NetworkPackage(20, 232, 31),
                        new NetworkPackage(21, 247, 25),
                        new NetworkPackage(22, 260, 31),
                        new NetworkPackage(23, 268, 36),
                        new NetworkPackage(24, 279, 8)
                ), 12, Arrays.asList(5, 16, 30, 47, 69, 105, 118, 126, 138, 159, 197, 215, 230, 243, 274, 274, 323,
                        331, 361, 398, 447, 478, 503, 534, 570)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 11, 45),
                        new NetworkPackage(1, 26, 22),
                        new NetworkPackage(2, 38, 24),
                        new NetworkPackage(3, 42, 49),
                        new NetworkPackage(4, 48, 39),
                        new NetworkPackage(5, 59, 3),
                        new NetworkPackage(6, 67, 1),
                        new NetworkPackage(7, 76, 5),
                        new NetworkPackage(8, 84, 30),
                        new NetworkPackage(9, 89, 37),
                        new NetworkPackage(10, 99, 12),
                        new NetworkPackage(11, 111, 6),
                        new NetworkPackage(12, 125, 33),
                        new NetworkPackage(13, 132, 20),
                        new NetworkPackage(14, 147, 16),
                        new NetworkPackage(15, 160, 7),
                        new NetworkPackage(16, 174, 15),
                        new NetworkPackage(17, 185, 14),
                        new NetworkPackage(18, 198, 9),
                        new NetworkPackage(19, 200, 37),
                        new NetworkPackage(20, 208, 18),
                        new NetworkPackage(21, 222, 3),
                        new NetworkPackage(22, 237, 28),
                        new NetworkPackage(23, 248, 10),
                        new NetworkPackage(24, 263, 11)
                ), 11, Arrays.asList(11, 56, 78, 102, 151, 190, 193, 194, 199, 229, 266, 278, 284, 317, -1, 337, -1,
                        -1, 344, 353, 390, 408, 411, -1, -1)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 0, 21),
                        new NetworkPackage(1, 10, 35),
                        new NetworkPackage(2, 10, 12),
                        new NetworkPackage(3, 21, 13),
                        new NetworkPackage(4, 35, 11),
                        new NetworkPackage(5, 35, 14),
                        new NetworkPackage(6, 51, 49),
                        new NetworkPackage(7, 59, 33),
                        new NetworkPackage(8, 59, 43),
                        new NetworkPackage(9, 67, 42),
                        new NetworkPackage(10, 80, 14),
                        new NetworkPackage(11, 93, 45),
                        new NetworkPackage(12, 93, 38),
                        new NetworkPackage(13, 100, 8),
                        new NetworkPackage(14, 101, 31),
                        new NetworkPackage(15, 108, 46),
                        new NetworkPackage(16, 123, 22),
                        new NetworkPackage(17, 127, 20),
                        new NetworkPackage(18, 139, 7),
                        new NetworkPackage(19, 142, 43),
                        new NetworkPackage(20, 142, 12),
                        new NetworkPackage(21, 142, 25),
                        new NetworkPackage(22, 154, 25),
                        new NetworkPackage(23, 154, 5),
                        new NetworkPackage(24, 154, 42)
                ), 7, Arrays.asList(0, 21, 56, 68, 81, 92, 106, 155, 188, -1, 231, 245, 290, -1, -1, 328, -1, -1, -1,
                        -1, -1, -1, -1, -1, -1)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 15, 23),
                        new NetworkPackage(1, 24, 44),
                        new NetworkPackage(2, 39, 43),
                        new NetworkPackage(3, 48, 15),
                        new NetworkPackage(4, 56, 6),
                        new NetworkPackage(5, 56, 8),
                        new NetworkPackage(6, 56, 29),
                        new NetworkPackage(7, 56, 28),
                        new NetworkPackage(8, 56, 4),
                        new NetworkPackage(9, 56, 17),
                        new NetworkPackage(10, 68, 44),
                        new NetworkPackage(11, 75, 22),
                        new NetworkPackage(12, 75, 34),
                        new NetworkPackage(13, 84, 46),
                        new NetworkPackage(14, 84, 21),
                        new NetworkPackage(15, 84, 25),
                        new NetworkPackage(16, 97, 31),
                        new NetworkPackage(17, 105, 34),
                        new NetworkPackage(18, 105, 43),
                        new NetworkPackage(19, 117, 17),
                        new NetworkPackage(20, 129, 12),
                        new NetworkPackage(21, 142, 47),
                        new NetworkPackage(22, 144, 22),
                        new NetworkPackage(23, 144, 18),
                        new NetworkPackage(24, 152, 9)
                ), 1, Arrays.asList(15, -1, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 84, -1, -1, -1, -1, -1, -1,
                        -1, 142, -1, -1, -1)),
                Arguments.of(Arrays.asList(
                        new NetworkPackage(0, 0, 0),
                        new NetworkPackage(1, 0, 0),
                        new NetworkPackage(2, 0, 0),
                        new NetworkPackage(3, 1, 0),
                        new NetworkPackage(4, 1, 0),
                        new NetworkPackage(5, 1, 1),
                        new NetworkPackage(6, 1, 2),
                        new NetworkPackage(7, 1, 3)
                ), 2, Arrays.asList(0, 0, 0, 1, 1, 1, 2, -1)));
    }


}


