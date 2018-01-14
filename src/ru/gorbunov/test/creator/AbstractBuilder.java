package ru.gorbunov.test.creator;

public abstract class AbstractBuilder {

    public static AbstractBuilder getInstance(String model){
        switch (model) {
            case "IPhone":
                return new IPhoneBuilder();
            case "IPad":
                return new IPodBuilder();
            case "IPod":
                return new IPadBuilder();
            default:
                throw new IllegalArgumentException("Invalid model: " + model);
        }
    };
    //default method
    public void buildColor(Color color) {};

    public abstract AppleDevice build();
}
