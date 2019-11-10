package ru.gorbunov.test.creator.abstractfactory;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.old.IPhoneBlack;
import ru.gorbunov.test.creator.devices.old.IPodBlack;

public class BlackAppleDeviceFactory implements AppleDeviceFactory {

    @Override
    public AppleDevice getIPhone() {
        return new IPhoneBlack();
    }

    @Override
    public AppleDevice getIPad() {
        return new IPodBlack();
    }

    @Override
    public AppleDevice getIPod() {
        return new IPodBlack();
    }
}
