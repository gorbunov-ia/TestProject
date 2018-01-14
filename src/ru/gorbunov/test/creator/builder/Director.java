package ru.gorbunov.test.creator.builder;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.modern.Color;

public class Director {
    private AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public Director setColor(String color) {
        builder.buildColor(Color.valueOf(color));
        return this;
    }

    public AppleDevice builAppledDevice() {
        AppleDevice device = builder.build();
        //logic
        device.validate();
        device.doLogic();

        return device;
    }
}
