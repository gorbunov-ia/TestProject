package ru.gorbunov.test.creator.builder;

import ru.gorbunov.test.creator.devices.AppleDevice;
import ru.gorbunov.test.creator.devices.modern.Color;

public abstract class AbstractBuilder {

    public static AbstractBuilder getInstance(String model){
        switch (model) {
            case "IPhone":
                return new IPhoneBuilder();
            case "IPad":
                return new IPadBuilder();
            case "IPod":
                return new IPodBuilder();
            default:
                throw new IllegalArgumentException("Invalid model: " + model);
        }
    };
    //default method
    public void buildColor(Color color) {};

    public abstract AppleDevice build();
}
