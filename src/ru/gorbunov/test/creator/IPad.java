package ru.gorbunov.test.creator;

public class IPad extends AppleDevice {
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
