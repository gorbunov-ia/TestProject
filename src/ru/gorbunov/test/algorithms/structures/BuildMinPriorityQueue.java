package ru.gorbunov.test.algorithms.structures;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Преобразование массива в min кучу.
 */
public class BuildMinPriorityQueue {

    public static void main(String[] args) {
//        queueTest();
        final int[] src = getInputSource();
        final AtomicInteger swapCount = new AtomicInteger();
        final StringBuilder swaps = new StringBuilder();
        new MinPriorityQueue(src, (str) -> {
            swapCount.incrementAndGet();
            swaps.append(str).append('\n');
        });
        System.out.println(swapCount.get());
        System.out.print(swaps.toString());
    }

    private static int[] getInputSource() {
        final Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        final int[] result = new int[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = scanner.nextInt();
        }
        return result;
//        return new int[] {7, 6, 5, 4, 3, 2};
    }

    private static void queueTest() {
        final MinPriorityQueue queue = new MinPriorityQueue(5);
        queue.insert(1);
        queue.insert(5);
        queue.insert(3);
        queue.changePriority(3, 6);
        queue.insert(3);
        queue.insert(2);
        queue.remove(5);
        queue.insert(4);
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.extractMin());
        }
    }

    private static class MinPriorityQueue {

        /**
         * 1..N
         */
        private final int[] data;
        private int size = 0;
        private Consumer<String> logger;

        public MinPriorityQueue(int maxSize) {
            data = new int[maxSize + 1];
        }

        /**
         * @param source array of 0..N-1
         */
        public MinPriorityQueue(int[] source) {
            this(source, (str) -> {
            });
        }

        public MinPriorityQueue(int[] source, Consumer<String> logger) {
            this.logger = logger;
            data = new int[source.length + 1];
            System.arraycopy(source, 0, data, 1, source.length);
            size = source.length;
            buildMinHeap();
        }

        private void buildMinHeap() {
            for (int i = size / 2; i > 0; i--) {
                shiftDown(i);
            }
        }

        private int getParent(int childIndex) {
            return childIndex / 2;
        }

        private int getLeftChild(int parentIndex) {
            return parentIndex * 2;
        }

        private int getRightChild(int parentIndex) {
            return parentIndex * 2 + 1;
        }

        private void swap(int indexFrom, int indexTo) {
            logger.accept((indexFrom - 1) + " " + (indexTo - 1));
            final int temp = data[indexFrom];
            data[indexFrom] = data[indexTo];
            data[indexTo] = temp;
        }

        private void shiftUp(int index) {
            int currentIndex = index;
            while (currentIndex > 1 && data[getParent(currentIndex)] > data[currentIndex]) {
                swap(getParent(currentIndex), currentIndex);
                currentIndex = getParent(currentIndex);
            }
        }

        private void shiftDown(int index) {
            int maxIndex = index;
            int l = getLeftChild(index);
            if (l <= size && data[l] < data[maxIndex]) {
                maxIndex = l;
            }
            int r = getRightChild(index);
            if (r <= size && data[r] < data[maxIndex]) {
                maxIndex = r;
            }
            if (index != maxIndex) {
                swap(index, maxIndex);
                shiftDown(maxIndex);
            }
        }

        public void insert(int priority) {
            if (size == data.length) {
                throw new IllegalArgumentException();
            }
            data[++size] = priority;
            shiftUp(size);
        }

        public int extractMin() {
            final int result = data[1];
            swap(1, size--);
            shiftDown(1);
            return result;
        }

        public void remove(int index) {
            data[index] = Integer.MIN_VALUE;
            shiftUp(index);
            extractMin();
        }

        public void changePriority(int index, int newPriority) {
            final int oldPriority = data[index];
            data[index] = newPriority;
            if (newPriority < oldPriority) {
                shiftUp(index);
            } else {
                shiftDown(index);
            }
        }
    }

}
