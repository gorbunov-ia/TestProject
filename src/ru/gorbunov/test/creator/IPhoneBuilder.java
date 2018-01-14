package ru.gorbunov.test.creator;

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
