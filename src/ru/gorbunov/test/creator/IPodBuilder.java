package ru.gorbunov.test.creator;

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