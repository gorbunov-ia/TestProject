package ru.gorbunov.test.algorithms;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HuffmanCode {
    public static void main(String[] args) {
        new HuffmanCode().process();
    }

    private void process() {
        final String str = getSource();
        Map<Character, Long> frequencyTable = getFrequencyTable(str);
        PriorityQueue<Node> queue = getPriorityQueue(frequencyTable);
        final Node finalTree = getTree(queue);
        final Map<Character, String> codeTable = finalTree.getCodeTable();
        String codeStr = getCodeStr(str, codeTable);

        System.out.println(frequencyTable.size() + " " + codeStr.length());
        codeTable.forEach((key, val) -> System.out.println(key + ": " + val));
        System.out.println(codeStr);
    }

    private String getCodeStr(String source, Map<Character, String> codeTable) {
        return String.join("", source.chars()
                .mapToObj(item -> codeTable.get((char) item))
                .collect(Collectors.toList()));
    }

    private PriorityQueue<Node> getPriorityQueue(Map<Character, Long> frequencyTable) {
        PriorityQueue<Node> queue = new PriorityQueue<>(frequencyTable.size());

        for (Map.Entry<Character, Long> entry : frequencyTable.entrySet()) {
            queue.add(new ListNode(entry.getKey(), entry.getValue()));
        }
        return queue;
    }

    private Node getTree(PriorityQueue<Node> queue) {
        for (int i = 0, n = queue.size() - 1; i < n; i++) {
            optimalStep(queue);
        }
        return queue.poll();
    }

    private void optimalStep(PriorityQueue<Node> queue) {
        final Node first = queue.poll();
        final Node second = queue.poll();
        queue.add(new InternalNode(first, second));
    }

    private Map<Character, Long> getFrequencyTable(String str) {
        return str.chars()
                .mapToObj(item -> ((char) item))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private String getSource() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("abacabad".getBytes()));
        //Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    abstract class Node implements Comparable<Node> {

        private final long weight;

        Node(long weight) {
            this.weight = weight;
        }

        long getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(weight, o.weight);
        }

        abstract Map<Character, String> getCodeTable();

        protected abstract Map<Character, String> getCodeTable(String currentCode);
    }

    class InternalNode extends Node {
        private final Node left;
        private final Node right;

        InternalNode(Node left, Node right) {
            super(left.weight + right.weight);
            this.left = Objects.requireNonNull(left);
            this.right = Objects.requireNonNull(right);
        }

        @Override
        Map<Character, String> getCodeTable() {
            return getCodeTable("");
        }

        protected Map<Character, String> getCodeTable(String currentCode) {
            final Map<Character, String> table = new HashMap<>();
            table.putAll(left.getCodeTable(currentCode + "0"));
            table.putAll(right.getCodeTable(currentCode + "1"));
            return table;
        }


        @Override
        public String toString() {
            return "InternalNode{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    class ListNode extends Node {
        private final Character symbol;

        ListNode(Character symbol, long weight) {
            super(weight);
            this.symbol = Objects.requireNonNull(symbol);
        }

        @Override
        Map<Character, String> getCodeTable() {
            return getCodeTable("0");
        }

        protected Map<Character, String> getCodeTable(String currentCode) {
            return Collections.singletonMap(symbol, currentCode);
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "weight=" + getWeight() +
                    ", symbol=" + symbol +
                    '}';
        }
    }

}
