public class TestCreator {
    public static void main(String[] args) {
        String color = "RED";
        String model = "IPhone";

        AppleDevice device = (new TestCreator()).createDevice(model, color);
        System.out.println(device);
    }

    public AppleDevice createDevice(String model, String color) {
        AppleDevice device = null;
        //JDK7+
        switch (model) {
            case "IPhone":
                switch (color) {
                    case "WHITE": device = new IPhonedWhite();
                        break;
                    case "BLACK": device = new IPhoneBlack();
                        break;
                    case "RED": device = new IPhoneRed();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid color: " + color);
                }
                break;
            case "IPad":
                switch (color) {
                    case "WHITE": device = new IPadWhite();
                        break;
                    case "BLACK": device = new IPadBlack();
                        break;
                    case "RED": device = new IPadRed();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid color: " + color);
                }
                break;
            case "IPod":
                switch (color) {
                    case "WHITE": device = new IPodWhite();
                        break;
                    case "BLACK": device = new IPodBlack();
                        break;
                    case "RED": device = new IPodRed();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid color: " + color);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid model: " + model);
        }

        //logic
        device.validate();
        device.doLogic();

        return device;
    }
}

//enum Color {RED, WHITE, BLACK}

class AppleDevice {

    public void validate(){};
    public void doLogic(){};
}

class IPhonedWhite extends AppleDevice {}
class IPhoneBlack extends AppleDevice {}
class IPhoneRed extends AppleDevice {}

class IPadWhite extends AppleDevice {}
class IPadBlack extends AppleDevice {}
class IPadRed extends AppleDevice {}

class IPodWhite extends AppleDevice {}
class IPodBlack extends AppleDevice {}
class IPodRed extends AppleDevice {}

