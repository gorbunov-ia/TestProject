package ru.gorbunov.test.creator.factorymethod;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.old.IPadWhite;
import ru.gorbunov.test.creator.devices.old.IPhonedWhite;
import ru.gorbunov.test.creator.devices.old.IPodWhite;

public class WhiteAppleDeviceFactory extends AppleDeviceFactory {
    @Override
    protected AppleDevice createDevice(String model) {
        switch (model) {
            case "IPhone":
                return new IPhonedWhite();
            case "IPad":
                return new IPadWhite();
            case "IPod":
                return new IPodWhite();
            default:
                throw new IllegalArgumentException("Invalid model: " + model);
        }
    }
}
