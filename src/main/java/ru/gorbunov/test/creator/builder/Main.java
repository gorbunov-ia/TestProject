package ru.gorbunov.test.creator.builder;

import ru.gorbunov.test.creator.devices.AppleDevice;

public class Main {
    public static void main(String[] args) {
        String color = "RED";
        String model = "IPhone";

        Director director = new Director(AbstractBuilder.getInstance(model));
        AppleDevice device = director.setColor(color).builAppledDevice();
        System.out.println(device);
    }
}
