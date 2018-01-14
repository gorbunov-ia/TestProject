package ru.gorbunov.test.jdk9;

public class Main {
    public static void main(String[] args) {
        ITestJDK9 test = new TestJDK9Impl();
        System.out.println("public: " + test.pblc());
        System.out.println("default: " + test.dflt());
        //System.out.println("private: " + test.prvt());
        System.out.println("private: " + ((TestJDK9Impl)test).prvt());

//        System.out.println("ITest2JDK9");
//        ITest2JDK9 test2 = new TestJDK9Impl();
//        System.out.println("default: " + test2.dflt());
    }
}
