package ru.gorbunov.test.algorithms.other.bloom;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

class StringFilterImpl implements Filter<String> {

    private final AtomicIntegerArray filter;
    private final Collection<Function<String, Integer>> hashFunctions;

    StringFilterImpl(int size, int hashFunctionCount) {
        if (size < 1 || hashFunctionCount < 1) {
            throw new IllegalArgumentException();
        }
        filter = new AtomicIntegerArray(size);
        hashFunctions = createHashFunctions(size, hashFunctionCount);
    }

    @Override
    public void add(String element) {
        executeForAllHashFunctions(element, filter::incrementAndGet);
    }

    @Override
    public void remove(String element) {
        if (contains(element) == Answer.NO) {
            throw new IllegalArgumentException();
        }
        executeForAllHashFunctions(element, filter::decrementAndGet);
    }

    @Override
    public Answer contains(String element) {
        for (Function<String, Integer> function : hashFunctions) {
            if (filter.get(function.apply(element)) == 0) {
                return Answer.NO;
            }
        }
        return Answer.MAYBE;
    }

    private List<Function<String, Integer>> createHashFunctions(int hashMaxValue, int functionCount) {
        final Random random = new Random();
        return random.ints(functionCount, 1, 999)
                .mapToObj(seed -> hashFunction(seed, hashMaxValue))
                .collect(Collectors.toList());
    }

    private Function<String, Integer> hashFunction(int seed, int maxValue) {
        return (str) -> positiveHashCode(seed, maxValue, str);
    }

    private int positiveHashCode(int seed, int maxValue, String value) {
        int h = 0;
        for (int i = 0; i < value.length(); i++) {
            h = seed * h + value.charAt(i);
        }
        return Math.abs(h % maxValue);
    }

    private void executeForAllHashFunctions(String element, Consumer<Integer> consumer) {
        hashFunctions.forEach(function -> consumer.accept(function.apply(element)));
    }
}
