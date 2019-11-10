package ru.gorbunov.test.jdk9;

public interface ITest2JDK9 {
    default int dflt() {
        System.out.println("From ITest2JDK9");
        return 10;
    }
}
