package ru.gorbunov.test.creator.factorymethod;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.old.IPadRed;
import ru.gorbunov.test.creator.devices.old.IPhoneRed;
import ru.gorbunov.test.creator.devices.old.IPodRed;

public class RedAppleDeviceFactory extends AppleDeviceFactory {
    @Override
    protected AppleDevice createDevice(String model) {
        switch (model) {
            case "IPhone":
                return new IPhoneRed();
            case "IPad":
                return new IPadRed();
            case "IPod":
                return new IPodRed();
            default:
                throw new IllegalArgumentException("Invalid model: " + model);
        }
    }
}
