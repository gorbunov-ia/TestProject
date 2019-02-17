package ru.gorbunov.test.algorithms.structures;

/**
 * Имплементация очереди с приоритетом на
 * основе max кучи.
 */
public class PriorityQueue {

    /**
     * 1..N
     */
    private final int[] data;
    private int size = 0;

    public PriorityQueue(int maxSize) {
        data = new int[maxSize + 1];
    }

    public static void main(String[] args) {
        final PriorityQueue queue = new PriorityQueue(16);
        queue.insert(1);
        queue.insert(5);
        queue.insert(3);
        queue.changePrioriry(3, 6);
        queue.insert(3);
        queue.insert(2);
        queue.remove(5);
        queue.insert(4);
        for(int i = 0; i < 5; i++) {
            System.out.println(queue.extractMax());
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
        final int temp = data[indexFrom];
        data[indexFrom] = data[indexTo];
        data[indexTo] = temp;
    }

    private void shiftUp(int index) {
        int currentIndex = index;
        while (currentIndex > 1 && data[getParent(currentIndex)] < data[currentIndex]) {
            swap(getParent(currentIndex), currentIndex);
            currentIndex = getParent(currentIndex);
        }
    }

    private void shiftDown(int index) {
        int maxIndex = index;
        int l = getLeftChild(index);
        if (l <= size && data[l] > data[maxIndex]) {
            maxIndex = l;
        }
        int r = getRightChild(index);
        if (r <= size && data[r] > data[maxIndex]) {
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

    public int extractMax() {
        final int result = data[1];
        swap(1, size--);
        shiftDown(1);
        return result;
    }

    public void remove(int index) {
        data[index] = Integer.MAX_VALUE;
        shiftUp(index);
        extractMax();
    }

    public void changePrioriry(int index, int newPrioriry) {
        final int oldPrioriry = data[index];
        data[index] = newPrioriry;
        if (newPrioriry > oldPrioriry) {
            shiftUp(index);
        } else {
            shiftDown(index);
        }
    }

}
