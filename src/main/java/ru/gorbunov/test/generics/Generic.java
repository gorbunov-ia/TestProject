package ru.gorbunov.test.generics;

import java.util.Collections;
import java.util.List;

public class Generic {

    static class Test<T> {

        private T field;

        public Test(T field) {
            this.field = field;
        }

        public T getField() {
            return field;
        }

        public <F> F test(F t) {
            T t2 = field;
            System.out.println(t2);
            return t;
        }

        public <G extends Comparable<G>> G test(List<G> list) {
            return Collections.max(list);
        }

    }

    public static void main(String[] args) {
        final Test<String> str = new Test<>("str");
        System.out.println(str.getField());
        System.out.println(str.test("qqq"));
        System.out.println(str.test(123));
    }

}
