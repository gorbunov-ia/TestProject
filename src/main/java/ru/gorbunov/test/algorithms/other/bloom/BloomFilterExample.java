package ru.gorbunov.test.algorithms.other.bloom;

public class BloomFilterExample {

    private static final String APPLE = "Apple";
    private static final String BANANA = "Banana";
    private static final String ORANGE = "Orange";

    public static void main(String[] args) {
        new BloomFilterExample().example();
    }

    private void example() {
        final Filter<String> filter = createBloomFilter();

        filter.add(APPLE);
        filter.add(BANANA);
        checkAndPrintAllFruits(filter);

        filter.remove(BANANA);
        checkAndPrintAllFruits(filter);
    }

    private Filter<String> createBloomFilter() {
        final int expectedElements = 3;
        final int size = optimalSize(expectedElements, 1);
        final int functionCount = optimalHashFunctionCount(size, expectedElements);
        return new StringFilterImpl(size, functionCount);
    }

    private int optimalSize(int expectedElements, int falsePositiveRate) {
        if (expectedElements < 0 || falsePositiveRate <= 0 || falsePositiveRate >= 100) {
            throw new IllegalArgumentException();
        }
        final double lnPositiveRate = Math.log(falsePositiveRate / 100.0);
        final double ln2 = Math.log(2);
        final double sqrLn2 = Math.pow(ln2 * ln2, 2);
        return (int) Math.floor( - expectedElements * lnPositiveRate / sqrLn2);
    }

    private int optimalHashFunctionCount(int filterSize, int expectedElements) {
        return (int) ((double) filterSize / expectedElements * Math.log(2));
    }

    private void checkAndPrintAllFruits(Filter<String> filter) {
        System.out.println("======");
        doPrint(filter, APPLE);
        doPrint(filter, BANANA);
        doPrint(filter, ORANGE);
    }

    private void doPrint(Filter<String> filter, String fruit) {
        System.out.println("Filter is contains " + fruit + ": " + filter.contains(fruit));
    }

}
