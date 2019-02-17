package ru.gorbunov.test.algorithms.other.bloom;

public class BloomFilterExample {
    public static void main(String[] args) {
        new BloomFilterExample().example();
    }

    private void example() {
        final Filter<String> filter = createBloomFilter();

        filter.add("Apple");
        filter.add("Banana");

        System.out.println(filter.contains("Apple"));
        System.out.println(filter.contains("Banana"));
        System.out.println(filter.contains("Orange"));
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

}
