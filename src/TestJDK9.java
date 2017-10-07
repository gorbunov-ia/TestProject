public class TestJDK9 {

    public void launch() {
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

class TestJDK9Impl implements ITestJDK9, ITest2JDK9 {

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
    protected int prvt() {
        System.out.println("From TestJDK9Impl");
        return 20;
    }

}

interface ITestJDK9 {

    int pblc();

    default int dflt() {
        return prvt() * prvt();
    }

    private int prvt() {
        System.out.println("From ITestJDK9");
        return 3;
    }
}

interface ITest2JDK9 {

    default int dflt() {
        System.out.println("From ITest2JDK9");
        return 10;
    }
}