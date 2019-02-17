package ru.gorbunov.test.algorithms.other.bloom;

interface Filter<T> {

    void add(T element);

    Answer contains(T element);

}
