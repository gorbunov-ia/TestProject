package ru.gorbunov.test.creator;

public class Main {
    public static void main(String[] args) {
        String color = "RED";
        String model = "IPhone";

        Director director = new Director(AbstractBuilder.getInstance(model));
        AppleDevice device = director.setColor(color).builAppledDevice();
        System.out.println(device);
    }
}
