package ru.gorbunov.test.creator;

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
