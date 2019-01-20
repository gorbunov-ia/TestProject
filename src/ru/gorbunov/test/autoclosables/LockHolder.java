package ru.gorbunov.test.autoclosables;

import java.util.Collection;

public class LockHolder implements AutoCloseable {
    private final Collection<AutoCloseable> locks;

    LockHolder(Collection<AutoCloseable> locks) {
        this.locks = locks;
    }


    @Override
    public void close() {
        for (AutoCloseable lock : locks) {
            try {
                lock.close();
            } catch (Exception e) {
                System.out.println("log exception");
            }
        }

    }
}
