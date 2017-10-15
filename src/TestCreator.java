public class TestCreator {
    public static void main(String[] args) {
        String color = "RED";
        String model = "IPhone";

        Director director = new Director(AbstractBuilder.getInstance(model));
        AppleDevice device = director.setColor(color).builAppledDevice();
        System.out.println(device);
    }
}

enum Color {RED, WHITE, BLACK}

class AppleDevice {

    public void validate(){};
    public void doLogic(){};
}

class IPhone extends AppleDevice {
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "IPhone{" +
                "color=" + color +
                '}';
    }
}

class IPad extends AppleDevice {
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "IPad{" +
                "color=" + color +
                '}';
    }
}

class IPod extends AppleDevice {
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "IPod{" +
                "color=" + color +
                '}';
    }
}

abstract class AbstractBuilder {

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

class IPhoneBuilder extends AbstractBuilder {
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

class IPodBuilder extends AbstractBuilder {
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

class IPadBuilder extends AbstractBuilder {
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

class Director {
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