package ru.gorbunov.test.creator.builder;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.modern.Color;
import ru.gorbunov.test.creator.devices.modern.IPad;

public class IPadBuilder extends AbstractBuilder {
    private final IPad instance;

    public IPadBuilder(){
        instance = new IPad();
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
