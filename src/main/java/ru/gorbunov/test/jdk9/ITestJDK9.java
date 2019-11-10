package ru.gorbunov.test.jdk9;

public interface ITestJDK9 {
    int pblc();

    default int dflt() {
        return prvt() * prvt();
    }

    private int prvt() {
        System.out.println("From ITestJDK9");
        return 3;
    }

}
