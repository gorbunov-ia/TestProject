package ru.gorbunov.test.creator.abstractfactory;

import ru.gorbunov.test.creator.devices.AppleDevice;

public class Main {
    public static void main(String[] args) {
        AppleDeviceFactory factory = new RedAppleDeviceFactory();
        AppleDevice device = factory.getIPhone();
        device.validate(); //!!!
        device.doLogic();
        System.out.println(device);
    }
}
