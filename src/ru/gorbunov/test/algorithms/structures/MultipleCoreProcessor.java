package ru.gorbunov.test.algorithms.structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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
        final List<Processor> processors = createProcessors(processorCount);
        final Queue<Task> taskQueue = createTasks(tasks);

        while (!taskQueue.isEmpty()) {
            final Task task = taskQueue.peek();
            for (Processor processor : processors) {
                if (processor.isFree()) {
                    processor.process(task);
                    result.add(new ProcessResult(processor.getNumber(), processor.getTaskStartTime()));
                    break;
                }
            }

            if (task.getStatus() != TaskStatus.WAIT) {
                taskQueue.poll();
            } else {
                processors.forEach(Processor::doWork);
            }

        }
        return result;
    }

    private Queue<Task> createTasks(List<Integer> tasks) {
        final Queue<Task> taskQueue = new LinkedList<>();
        tasks.forEach(duration -> taskQueue.add(new Task(duration)));
        return taskQueue;
    }

    private List<Processor> createProcessors(int processorCount) {
        final List<Processor> processors = new ArrayList<>(processorCount);
        for (int i = 0; i < processorCount; i++) {
            processors.add(new Processor(i));
        }
        return processors;
    }

    private static class Processor {
        private final int number;
        private long ticks;

        private Task currentTask;
        private long taskStartTime;

        private Processor(int number) {
            this.number = number;
        }

        void process(Task task) {
            task.setStatus(task.getDuration() != 0 ? TaskStatus.ACTIVE : TaskStatus.COMPLETE);
            currentTask = task;
            taskStartTime = ticks;
        }

        boolean isFree() {
            return currentTask == null || currentTask.getStatus() == TaskStatus.COMPLETE;
        }

        void doWork() {
            ticks++;
            if (currentTask != null && taskStartTime + currentTask.getDuration() <= ticks) {
                currentTask.setStatus(TaskStatus.COMPLETE);
            }
        }

        int getNumber() {
            return number;
        }

        long getTaskStartTime() {
            return taskStartTime;
        }

        @Override
        public String toString() {
            return "Processor{" +
                    "number=" + number +
                    '}';
        }
    }

    private static class Task {
        private final long duration;
        private TaskStatus status = TaskStatus.WAIT;

        Task(long duration) {
            this.duration = duration;
        }

        long getDuration() {
            return duration;
        }

        TaskStatus getStatus() {
            return status;
        }

        void setStatus(TaskStatus status) {
            this.status = status;
        }
    }

    private enum TaskStatus {
        WAIT,
        ACTIVE,
        COMPLETE
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
