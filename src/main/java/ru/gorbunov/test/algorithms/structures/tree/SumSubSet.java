package ru.gorbunov.test.algorithms.structures.tree;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeSet;

public class SumSubSet {

    private static final int MOD = 1_000_000_001;
    private long seed = 0;

    public static void main(String[] args) {
//        new SumSubSet().process(System.in);
        new SumSubSet().process(new ByteArrayInputStream(("69\n" +
                "s 40279559 89162572\n" +
                "- 774613289\n" +
                "s 869592654 915517087\n" +
                "- 165280355\n" +
                "- 776346290\n" +
                "- 221187096\n" +
                "s 421986248 742826969\n" +
                "s 83228103 852190011\n" +
                "- 640319482\n" +
                "? 528689193\n" +
                "? 75245219\n" +
                "- 617070033\n" +
                "+ 66257759\n" +
                "s 25751289 70170547\n" +
                "s 28248247 617849094\n" +
                "- 954357244\n" +
                "+ 477444954\n" +
                "? 608389416\n" +
                "s 400483980 423330836\n" +
                "- 477444954\n" +
                "? 441393551\n" +
                "s 66257759 66257759\n" +
                "- 822218158\n" +
                "? 806479414\n" +
                "s 548665149 925635534\n" +
                "s 66257759 66257759\n" +
                "? 234121006\n" +
                "+ 663305907\n" +
                "s 314809050 685231317\n" +
                "- 0\n" +
                "s 487458874 602635501\n" +
                "s 66257759 66257759\n" +
                "? 918193520\n" +
                "? 606474691\n" +
                "s 188185089 774086933\n" +
                "- 322445571\n" +
                "s 66257759 66257759\n" +
                "- 814123984\n" +
                "s 0 0\n" +
                "s 0 0\n" +
                "s 689260392 827869844\n" +
                "? 204276815\n" +
                "- 66257759\n" +
                "? 488766408\n" +
                "s 412617563 631410280\n" +
                "- 463415495\n" +
                "+ 601030115\n" +
                "? 776513589\n" +
                "s 257003372 887483600\n" +
                "+ 154047223\n" +
                "? 154047223\n" +
                "? 219327735\n" +
                "+ 978812473\n" +
                "s 978812473 154047223\n" +
                "? 718062555\n" +
                "? 128066784\n" +
                "- 15718305\n" +
                "? 754978417\n" +
                "s 643892549 819127300\n" +
                "? 192401474\n" +
                "? 643892549\n" +
                "+ 638898307\n" +
                "? 973173529\n" +
                "+ 506709268\n" +
                "- 506709268\n" +
                "+ 744166533\n" +
                "- 638898307\n" +
                "+ 95240753\n" +
                "s 997348833 63778002").getBytes()));
    }

    private void process(InputStream inputStream) {
        final NavigableSet<Integer> set = new TreeSet<>();
        Scanner scanner = new Scanner(inputStream);
        int commandSize = Integer.valueOf(scanner.nextLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < commandSize; i++) {
            String command = scanner.nextLine();
            builder.append(command).append('\n');
            switch (command.substring(0, 1)) {
                case "?":
                    System.out.println(set.contains(getArg(command)) ? "Found" : "Not found");
                    break;
                case "+":
                    set.add(getArg(command));
                    break;
                case "-":
                    set.remove(getArg(command));
                    break;
                case "s": {
                    int spaceSymbol = command.lastIndexOf(' ');
                    int from = Integer.parseInt(command.substring(2, spaceSymbol));
                    int to = Integer.parseInt(command.substring(spaceSymbol + 1));
                    if (hashIndex(from) > hashIndex(to)) {
                        throw new IllegalArgumentException("From :" + from + ", to:" + to + ", s:" + seed + ", commands: " + builder);
                    }
                    NavigableSet<Integer> subSet = set.subSet(hashIndex(from), true, hashIndex(to), true);
                    long sum = subSet.stream().mapToLong(val -> val).sum();
                    System.out.println(sum);
                    seed = sum;
                    break;
                }
            }
        }
    }


    private int hashIndex(int rawIndex) {
        return (int)((seed + rawIndex) % MOD);
    }

    private Integer getArg(String line) {
        int arg = Integer.parseInt(line.substring(2));
        return hashIndex(arg);
    }
}
