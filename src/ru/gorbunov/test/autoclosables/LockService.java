package ru.gorbunov.test.autoclosables;

public interface LockService {
    AutoCloseable lock(String key);
    void unlock(String key);
}
