package ru.gorbunov.test.jdk9;

public class TestJDK9Impl implements ITestJDK9, ITest2JDK9 {

    @Override
    public int pblc() {
        return 5;
    }

    @Override
    public int dflt() {
//        return ((ITestJDK9)this).dflt(); //java.lang.StackOverflowError
//        return new ITest2JDK9() {}.dflt();
//        return new ITestJDK9() {
//            @Override
//            public int pblc() {return 0;}
//        }.dflt();
        return ITestJDK9.super.dflt();
    }

    //private int prvt() {

    /*protected*/public int prvt() {
        System.out.println("From TestJDK9Impl");
        return 20;
    }


}