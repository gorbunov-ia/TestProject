package ru.gorbunov.test.creator.builder;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.modern.Color;
import ru.gorbunov.test.creator.devices.modern.IPod;

public class IPodBuilder extends AbstractBuilder {
    private final IPod instance;

    public IPodBuilder(){
        instance = new IPod();
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