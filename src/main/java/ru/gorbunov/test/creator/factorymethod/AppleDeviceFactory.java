package ru.gorbunov.test.creator.factorymethod;

import ru.gorbunov.test.creator.devices.AppleDevice;

public abstract class AppleDeviceFactory {

    protected abstract AppleDevice createDevice(String model);

    public AppleDevice getDevice(String model) {
        AppleDevice appleDevice;
        appleDevice = createDevice(model);
        appleDevice.validate();
        appleDevice.doLogic();
        return appleDevice;
    }

}
