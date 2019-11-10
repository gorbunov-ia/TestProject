package ru.gorbunov.test.creator.abstractfactory;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.old.IPadRed;
import ru.gorbunov.test.creator.devices.old.IPhoneRed;
import ru.gorbunov.test.creator.devices.old.IPodRed;

public class RedAppleDeviceFactory implements AppleDeviceFactory {
    @Override
    public AppleDevice getIPhone() {
        return new IPhoneRed();
    }

    @Override
    public AppleDevice getIPad() {
        return new IPadRed();
    }

    @Override
    public AppleDevice getIPod() {
        return new IPodRed();
    }
}
