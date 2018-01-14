package ru.gorbunov.test.creator.factorymethod;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.old.IPadBlack;
import ru.gorbunov.test.creator.devices.old.IPhoneBlack;
import ru.gorbunov.test.creator.devices.old.IPodBlack;

public class BlackAppleDeviceFactory extends AppleDeviceFactory {
    @Override
    protected AppleDevice createDevice(String model) {
        switch (model) {
            case "IPhone":
                return new IPhoneBlack();
            case "IPad":
                return new IPadBlack();
            case "IPod":
                return new IPodBlack();
            default:
                throw new IllegalArgumentException("Invalid model: " + model);
        }
    }
}
