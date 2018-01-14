package ru.gorbunov.test.creator.builder;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.modern.Color;
import ru.gorbunov.test.creator.devices.modern.IPhone;

public class IPhoneBuilder extends AbstractBuilder {
    private final IPhone instance;

    public IPhoneBuilder(){
        instance = new IPhone();
    }

    @Override
    public void buildColor(Color color) {
        instance.setColor(color);
    }

    @Override
    public AppleDevice build() {
        return instance;
    }

}
