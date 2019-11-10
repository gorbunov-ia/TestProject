package ru.gorbunov.test.algorithms.structures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Расстановка скобок в коде.
 */
public class BracketValidator {
    private LinkedList<Bracket> stack = new LinkedList<>();
    private static final Set<Character> BRACKETS = new HashSet<>(Arrays.asList('(',')','[',']','{','}'));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(new BracketValidator().process(scanner.nextLine()));
    }

    public String process(String line) {
        for (int i = 0; i < line.length(); i++) {
            char bracket = line.charAt(i);
            if (!BRACKETS.contains(bracket)) {
                continue;
            }
            if (bracket == '(' || bracket == '[' || bracket == '{') {
                stack.push(new Bracket(bracket, i + 1));
                continue;
            }
            final Bracket prevBracket = stack.poll();
            if (prevBracket == null) {
                return String.valueOf(i + 1);
            }
            if (prevBracket.character == '[' && bracket != ']'
                    || prevBracket.character == '(' && bracket != ')'
                    || prevBracket.character == '{' && bracket != '}') {
                return String.valueOf(i + 1);
            }
        }
        return stack.isEmpty() ? "Success" : String.valueOf(stack.peekLast().position);
    }

    private class Bracket {
        private final Character character;
        private final int position;

        Bracket(Character character, int position) {
            this.character = character;
            this.position = position;
        }
    }

    @ParameterizedTest
    @MethodSource("createTestData")
    public void test(String input, String result) {
        Assertions.assertEquals(result, process(input));
    }

    private static Stream<Arguments> createTestData() {
        return Stream.of(
                Arguments.of("([](){([])})", "Success"),
                Arguments.of("()[]}", "5"),
                Arguments.of("{{[()]]", "7"),
                Arguments.of("{}([]", "3"),
                Arguments.of("foo(bar)", "Success"),
                Arguments.of("foo(bar[i)", "10"));
    }

}
