package ru.gorbunov.test.creator.abstractfactory;

import ru.gorbunov.test.creator.devices.AppleDevice;

public interface AppleDeviceFactory {
    AppleDevice getIPhone();
    AppleDevice getIPad();
    AppleDevice getIPod();
}
