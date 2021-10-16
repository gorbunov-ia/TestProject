package ru.gorbunov.test.algorithms.other;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent repeated
 * successive characters as a single count and character. For example, the string "111122233411" would be encoded
 * as "4132231421". Write class that will implement Iterator interface and would be able to iterate over the encoded
 * string to produce decoded result (every call to next() method should produce one number at a time).
 * However, for simplicity, we will only operate with numbers.
 * Please, note that 0 is a valid number in the sequence.
 * <p>
 * Example input sequence #1:
 * 1, 3, 4, 1
 * Example output sequence #1:
 * 3, 1, 1, 1, 1
 * <p>
 * Example sequence #2:
 * 4, 8, 2, 7, 1, 4
 * Example output #2:
 * 8888774
 **/
public class StringDecoderIterator implements Iterator<Integer> {

    private final String str;

    private int index = -1;
    private int count = -1;

    public StringDecoderIterator(String str) {
        if (Objects.requireNonNull(str, "String should not be null").length() % 2 != 0) {
            throw new IllegalArgumentException("Length should be even");
        }
        StringBuilder builder = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch < '0' || ch > '9') {
                throw new IllegalArgumentException("Input contains illegal character " + ch);
            }
            if (ch == '0' && i % 2 == 0) {
                i++;
            } else {
                builder.append(ch);
            }
        }
        this.str = builder.toString();
    }

    @Override
    public boolean hasNext() {
        if (str.length() == 0) {
            return false;
        }
        if (count > 0) {
            return true;
        }
        return index < str.length() - 1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (count > 0) {
            return returnNumber();
        }
        index++;
        count = getNumber();
        index++;
        return returnNumber();
    }

    private int getNumber() {
        return str.charAt(index) - '0';
    }

    private int returnNumber() {
        count--;
        return getNumber();
    }

}
