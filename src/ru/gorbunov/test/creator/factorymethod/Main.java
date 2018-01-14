package ru.gorbunov.test.creator.factorymethod;

import ru.gorbunov.test.creator.devices.AppleDevice;

public class Main {
    public static void main(String[] args) {
        String model = "IPhone";

        AppleDeviceFactory factory = new RedAppleDeviceFactory();
        AppleDevice device = factory.getDevice(model);
        System.out.println(device);
    }
}
