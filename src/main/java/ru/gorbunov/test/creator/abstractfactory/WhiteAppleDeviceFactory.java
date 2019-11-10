package ru.gorbunov.test.creator.abstractfactory;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.old.IPadWhite;
import ru.gorbunov.test.creator.devices.old.IPhonedWhite;
import ru.gorbunov.test.creator.devices.old.IPodWhite;

public class WhiteAppleDeviceFactory implements AppleDeviceFactory {
    @Override
    public AppleDevice getIPhone() {
        return new IPhonedWhite();
    }

    @Override
    public AppleDevice getIPad() {
        return new IPadWhite();
    }

    @Override
    public AppleDevice getIPod() {
        return new IPodWhite();
    }
}
