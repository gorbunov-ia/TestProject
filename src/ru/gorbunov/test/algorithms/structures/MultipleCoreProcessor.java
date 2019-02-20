package ru.gorbunov.test.algorithms.structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * По данным n процессорам и m задач определите, для каждой из задач,
 * каким процессором она будет обработана.
 * Вход. Число процессоров n и последовательность чисел t(0),...,t(m−1),
 * где t(i) — время, необходимое на обработку i-й задачи.
 * Выход. Для каждой задачи определите, какой процессор
 * и в какое время начнёт её обрабатывать, предполагая, что
 * каждая задача поступает на обработку первому освободив-
 * шемуся процессору.
 */
public class MultipleCoreProcessor {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int processorCount = scanner.nextInt();
        final int taskCount = scanner.nextInt();
        final List<Integer> tasks = new ArrayList<>(taskCount);
        for (int i = 0; i < taskCount; i++) {
            tasks.add(scanner.nextInt());
        }

        final List<ProcessResult> results = new MultipleCoreProcessor().dispatch(processorCount, tasks);

        final StringBuilder builder = new StringBuilder(results.size() * 5);
        results.forEach(result -> builder.append(result).append('\n'));
        System.out.println(builder.toString());
    }

    private List<ProcessResult> dispatch(int processorCount, List<Integer> tasks) {
        final List<ProcessResult> result = new ArrayList<>(tasks.size());
        final Queue<Processor> processors = createProcessors(processorCount);
        final Queue<Task> taskQueue = createTasks(tasks);

        while (!taskQueue.isEmpty()) {
            final Task task = taskQueue.poll();
            final Processor processor = processors.poll();

            processor.process(task, processor.getTaskEndTime());
            result.add(new ProcessResult(processor.getNumber(), processor.getTaskStartTime()));
            processors.add(processor);
        }
        return result;
    }

    private Queue<Task> createTasks(List<Integer> tasks) {
        final Queue<Task> taskQueue = new LinkedList<>();
        tasks.forEach(duration -> taskQueue.add(new Task(duration)));
        return taskQueue;
    }

    private Queue<Processor> createProcessors(int processorCount) {
        final PriorityQueue<Processor> processors = new PriorityQueue<>(processorCount);
        for (int i = 0; i < processorCount; i++) {
            processors.add(new Processor(i));
        }
        return processors;
    }

    private static class Processor implements Comparable<Processor> {
        private final int number;

        private Task currentTask;
        private long taskStartTime;

        private Processor(int number) {
            this.number = number;
        }

        void process(Task task, long taskStartTime) {
            currentTask = task;
            this.taskStartTime = taskStartTime;
        }

        int getNumber() {
            return number;
        }

        long getTaskStartTime() {
            return taskStartTime;
        }

        long getTaskEndTime() {
            return currentTask == null ? 0 : taskStartTime + currentTask.getDuration();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Processor processor = (Processor) o;
            return number == processor.number;
        }

        @Override
        public int hashCode() {

            return Objects.hash(number);
        }

        @Override
        public String toString() {
            return "Processor{" +
                    "number=" + number +
                    '}';
        }

        @Override
        public int compareTo(Processor o) {
            return Comparator.comparing(Processor::getTaskEndTime)
                    .thenComparing(Processor::getNumber)
                    .compare(this, o);
        }
    }

    private static class Task {
        private final long duration;

        Task(long duration) {
            this.duration = duration;
        }

        long getDuration() {
            return duration;
        }

    }

    private static class ProcessResult {
        private final int processorNumber;
        private final long startTime;

        private ProcessResult(int processorNumber, long startTime) {
            this.processorNumber = processorNumber;
            this.startTime = startTime;
        }

        @Override
        public String toString() {
            return processorNumber + " " + startTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProcessResult that = (ProcessResult) o;
            return processorNumber == that.processorNumber &&
                    startTime == that.startTime;
        }

        @Override
        public int hashCode() {

            return Objects.hash(processorNumber, startTime);
        }
    }

    @ParameterizedTest
    @MethodSource("createTestData")
    public void test(int processorCount, List<Integer> tasks, List<ProcessResult> result) {
        Assertions.assertEquals(result, new MultipleCoreProcessor().dispatch(processorCount, tasks));
    }

    private static Stream<Arguments> createTestData() {
        return Stream.of(
                Arguments.of(2, Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(
                        new ProcessResult(0, 0),
                        new ProcessResult(1, 0),
                        new ProcessResult(0, 1),
                        new ProcessResult(1, 2),
                        new ProcessResult(0, 4))),
                Arguments.of(4, Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        Arrays.asList(
                                new ProcessResult(0, 0),
                                new ProcessResult(1, 0),
                                new ProcessResult(2, 0),
                                new ProcessResult(3, 0),
                                new ProcessResult(0, 1),
                                new ProcessResult(1, 1),
                                new ProcessResult(2, 1),
                                new ProcessResult(3, 1),
                                new ProcessResult(0, 2),
                                new ProcessResult(1, 2),
                                new ProcessResult(2, 2),
                                new ProcessResult(3, 2),
                                new ProcessResult(0, 3),
                                new ProcessResult(1, 3),
                                new ProcessResult(2, 3),
                                new ProcessResult(3, 3),
                                new ProcessResult(0, 4),
                                new ProcessResult(1, 4),
                                new ProcessResult(2, 4),
                                new ProcessResult(3, 4)))
        );
    }


}
