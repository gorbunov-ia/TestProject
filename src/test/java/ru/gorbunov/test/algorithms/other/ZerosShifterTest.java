package ru.gorbunov.test.algorithms.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZerosShifterTest {

    private ZerosShifter shifter;

    @BeforeEach
    void setUp() {
        shifter = new ZerosShifter();
    }

    @Test
    void zerosShouldBeShifted() {
        int[] data = {8, 9, 0, 3, 0, 0, 5};

        shifter.shiftZeroes(data);

        Assertions.assertArrayEquals(new int[]{8, 9, 3, 5, 0, 0, 0}, data);
    }

    @Test
    void allZeros() {
        int[] data = {0, 0, 0};

        shifter.shiftZeroes(data);

        Assertions.assertArrayEquals(new int[]{0, 0, 0}, data);
    }

    @Test
    void withoutZeros() {
        int[] data = {1, 1, 1};

        shifter.shiftZeroes(data);

        Assertions.assertArrayEquals(new int[]{1, 1, 1}, data);
    }

    @Test
    void lastValue() {
        int[] data = {0, 0, 1};

        shifter.shiftZeroes(data);

        Assertions.assertArrayEquals(new int[]{1, 0, 0}, data);
    }

    @Test
    void firstValue() {
        int[] data = {1, 0, 0};

        shifter.shiftZeroes(data);

        Assertions.assertArrayEquals(new int[]{1, 0, 0}, data);
    }
}