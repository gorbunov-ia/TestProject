package ru.gorbunov.test.runnable;

import java.util.concurrent.TimeUnit;

public class RuntimeExceptionInAnotherThread {

    public static void main(String[] args) throws InterruptedException {
        final Runnable work = new Runnable() {

            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("gg");
            }
        };

        new Thread(work).start();

        TimeUnit.SECONDS.sleep(5);

        System.out.println("end");

    }
}
