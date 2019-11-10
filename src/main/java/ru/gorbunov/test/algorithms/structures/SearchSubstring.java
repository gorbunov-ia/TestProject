package ru.gorbunov.test.algorithms.structures;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Поиск образца в тексте
 * Найти все вхождения строки Pattern в строку Text.
 * Вход. Строки Pattern и Text.
 * Выход. Все индексы i строки Text, начиная с которых
 * строка Pattern входит в Text:
 * Text[i..i + |Pattern| − 1] = Pattern.
 * Реализуйте алгоритм Карпа–Рабина.
 */
public class SearchSubstring {
    public static void main(String[] args) {
        new SearchSubstring().process();
    }

    private void process() {
        final InputStream input = createInputStream();
        final Scanner scanner = new Scanner(input);
        final String pattern = scanner.nextLine();
        final String text = scanner.nextLine();

        final List<String> result = new StringMatcher(pattern, text).match();

        if (result.isEmpty()) {
            System.out.println("");
            return;
        }
        final Iterator<String> iterator = result.iterator();
        final StringBuilder stringBuilder = new StringBuilder(iterator.next());
        while (iterator.hasNext()) {
            stringBuilder.append(" ").append(iterator.next());
        }
        System.out.println(stringBuilder);
    }

    private InputStream createInputStream() {
        return new ByteArrayInputStream(("aba\n" +
                "abacaba").getBytes());
    }

    private static class StringMatcher {

        private static final int x = 263;
        private static final int p = 1_000_000_007;

        private final char[] pattern;
        private final char[] text;

        private long patternHash;
        private long[] cachePowers;


        StringMatcher(String pattern, String text) {
            this.pattern = pattern.toCharArray();
            this.text = text.toCharArray();
            cachePowers = new long[pattern.length()];
            cachePowers[0] = 1;
        }

        List<String> match() {
            init();
            return doMatch();
        }

        private List<String> doMatch() {
            final LinkedList<String> result = new LinkedList<>();
            boolean firstWindow = true;
            long windowHash = 0;
            for (int i = text.length - pattern.length; i >= 0; i--) {
                final int to = i + pattern.length;
                if (firstWindow) {
                    windowHash = calculateHash(text, i, to);
                    firstWindow = false;
                } else {
                    windowHash = recalculateHash(windowHash, text[i], text[to]);
                }
                if (windowHash == patternHash && isEquals(i, to)) {
                    result.addFirst(String.valueOf(i));
                }
            }
            return result;
        }

        private boolean isEquals(int from, int to) {
            for (int i = from; i < to; i++) {
                if (text[i] != pattern[i - from]) {
                    return false;
                }
            }
            return true;
        }

        private long recalculateHash(long windowHash, char newChar, char lastChar) {
            final long lastCharHash = (lastChar * cachePowers[cachePowers.length - 1]) % p;
            final long lastHash = (windowHash - lastCharHash) % p;
            final long normalizeLastHash = (lastHash + p) % p;
            final long newHash = (normalizeLastHash * x) % p;
            return (newHash + newChar) % p;
        }

        private void init() {
            patternHash = calculateHash(pattern, 0, pattern.length);
        }

        private long calculateHash(char[] str, int from, int to) {
            long result = str[from];
            for (int i = 1; i < to - from; i++) {
                final char current = str[from + i];
                long currentPowerOfX = cachePowers[i];
                if (currentPowerOfX == 0) {
                    currentPowerOfX = (cachePowers[i - 1] * x) % p;
                    cachePowers[i] = currentPowerOfX;
                }
                result = (result + ((current * currentPowerOfX) % p)) % p;
            }
            return result;
        }
    }
}
