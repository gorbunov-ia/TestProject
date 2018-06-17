package ru.gorbunov.test.algorithms;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HuffmanDecode {

    public static void main(String[] args) {
        new HuffmanDecode().process();
    }


    private void process() {
        final DecodeParameter parameter = getParameter();
        String currentCode = "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parameter.codeSize; i++) {
            currentCode += parameter.code.charAt(i);
            if (parameter.codeTable.get(currentCode) != null) {
                result.append(parameter.codeTable.get(currentCode));
                currentCode = "";
            }
        }
        System.out.println(result.toString());
    }

    private DecodeParameter getParameter() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("4 14\na: 0\nb: 10\nc: 110\nd: 111\n01001100100111".getBytes()));
//        Scanner scanner = new Scanner(new ByteArrayInputStream("1 1\na: 0\n0".getBytes()));
        //Scanner scanner = new Scanner(System.in);
        DecodeParameter parameter = new DecodeParameter();
        final int tableSize = scanner.nextInt();
        parameter.codeSize = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < tableSize; i++) {
            final String tableStr = scanner.nextLine();
            parameter.codeTable.put(tableStr.substring(3), tableStr.charAt(0));
        }
        parameter.code = scanner.next();
        return parameter;
    }

    private class DecodeParameter {
        private String code;
        private Integer codeSize;
        private Map<String, Character> codeTable = new HashMap<>();
    }
}
