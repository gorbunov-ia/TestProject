package ru.gorbunov.test.algorithms.methods;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Первая строка содержит количество предметов 1≤n≤10^3 и вместимость рюкзака 0≤W≤2⋅10^6. Каждая из следующих n строк
 * задаёт стоимость 0≤c_i≤2⋅10^6 и объём 0<w_i≤2⋅10^6 предмета (n, W, c_i, w_i — целые числа).
 * Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть,
 * стоимость и объём при этом пропорционально уменьшатся), помещающихся в данный рюкзак,
 * с точностью не менее трёх знаков после запятой.
 */
public class Backpack {

    public static void main(String[] args) {
        new Backpack().process();
    }

    private void process() {
        Scanner scanner = new Scanner(new ByteArrayInputStream("1 10\n500 30".getBytes()));
//        Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        Pack pack = new Pack(scanner.nextInt());
        final List<Item> items = getItems(length, scanner);
        final double maxPackPrice = getMaxPackPrice(pack, items);
        System.out.printf("%.3f", maxPackPrice);
    }

    private double getMaxPackPrice(Pack pack, List<Item> items) {
        pack.load(items);
        return pack.price;
    }

    private List<Item> getItems(int length, Scanner scanner) {
        List<Item> items = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            items.add(new Item(scanner.nextInt(), scanner.nextInt()));
        }
        final Optional<Item> first = items.stream().filter(item -> item.price == 0).findFirst();
        if (first.isPresent()) {
            throw new NullPointerException(items.toString());
        }
        return items;
    }

    class Item implements Comparable<Item> {
        private final int price;
        private final int weight;
        private final double weightUnitPrice;


        Item(int price, int weight) {
            this.price = price;
            this.weight = weight;
            if (weight == 0d) {
                weightUnitPrice = 0;
            } else {
                weightUnitPrice = price * 1d / weight;
            }

        }

        @Override
        public int compareTo(Item o) {
            return Double.compare(o.weightUnitPrice, weightUnitPrice);
        }

        @Override
        public String toString() {
            return "Item{" +
                    "price=" + price +
                    ", weight=" + weight +
                    ", weightUnitPrice=" + weightUnitPrice +
                    '}';
        }
    }

    class Pack {
        private int maxWeight;
        private double weight = 0d;
        private double price = 0d;

        Pack(int maxWeight) {
            this.maxWeight = maxWeight;
        }

        void load(List<Item> items) {
//            System.out.println(items);
            items.sort(null);
            System.out.println(items);
            items.forEach(this::addItem);
        }

        private void addItem(Item item) {
            double weightForFullPack = maxWeight - weight;
            if (weightForFullPack == 0) {
                return;
            }
            if (weightForFullPack > item.weight) {
                price += item.price;
                weight += item.weight;
            } else {
                price += item.weightUnitPrice * weightForFullPack;
                weight += weightForFullPack;
            }

        }

        @Override
        public String toString() {
            return "Pack{" +
                    "maxWeight=" + maxWeight +
                    '}';
        }
    }

}
