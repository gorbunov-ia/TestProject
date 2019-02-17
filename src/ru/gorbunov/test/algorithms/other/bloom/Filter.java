package ru.gorbunov.test.algorithms.other.bloom;

interface Filter<T> {

    void add(T element);

    void remove(T element);

    Answer contains(T element);

}
