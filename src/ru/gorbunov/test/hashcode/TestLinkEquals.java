package ru.gorbunov.test.hashcode;

public class TestLinkEquals {
    public static void main(String[] args) {
        A a1 = new B();
        a1.equals(null);
        B b1 = new B();
        b1.equals(null);
    }
}

class A {
    @Override
    public boolean equals(Object obj) {
        System.out.println("A.equals");
        //return super.equals(obj);
        return false;
    }
}

class B extends A {
    @Override
    public boolean equals(Object obj) {
        System.out.println("B.equals");
        //return super.equals(obj);
        return false;
    }
}

