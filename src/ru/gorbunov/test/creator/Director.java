package ru.gorbunov.test.creator;

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
