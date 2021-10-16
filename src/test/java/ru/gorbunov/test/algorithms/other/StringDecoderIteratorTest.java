package ru.gorbunov.test.algorithms.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringDecoderIteratorTest {

    @Test
    void example1() {
        StringDecoderIterator iterator = new StringDecoderIterator("1341");

        assertNext(iterator, 1, 3);
        assertNext(iterator, 4, 1);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void example2() {
        StringDecoderIterator iterator = new StringDecoderIterator("482714");

        assertNext(iterator, 4, 8);
        assertNext(iterator, 2, 7);
        assertNext(iterator, 1, 4);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void inputContainsZeroGroup() {
        StringDecoderIterator iterator = new StringDecoderIterator("0221");

        assertNext(iterator, 2, 1);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void inputContainsSingleZeroGroup() {
        StringDecoderIterator iterator = new StringDecoderIterator("02");

        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void inputContainsSeveralZeroGroup() {
        StringDecoderIterator iterator = new StringDecoderIterator("020112");

        assertNext(iterator, 1, 2);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void inputContainsZeroNumber() {
        StringDecoderIterator iterator = new StringDecoderIterator("20");

        assertNext(iterator, 2, 0);
        Assertions.assertFalse(iterator.hasNext());
    }

    private void assertNext(StringDecoderIterator iterator, int count, int value) {
        for (int i = 0; i < count; i++) {
            Assertions.assertTrue(iterator.hasNext());
            Assertions.assertEquals(value, iterator.next(), "Number of digit " + i);
        }
    }
}