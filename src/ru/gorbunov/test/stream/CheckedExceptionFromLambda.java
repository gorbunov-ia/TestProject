package ru.gorbunov.test.stream;


public class CheckedExceptionFromLambda {

    public static void main(String[] args) {
        final CheckedConsumer<Integer> checkedConsumer = CheckedExceptionFromLambda::myConsumer;

        try {
            method(1, checkedConsumer);
            method(10, checkedConsumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void myConsumer(Integer value) throws Exception {
        if (value.equals(10)) {
            throw new Exception("surprise");
        }
    }

    private static void method(Integer value, CheckedConsumer<Integer> checkedConsumer) throws Exception {
        checkedConsumer.accept(value);
    }

}

@FunctionalInterface
interface CheckedConsumer<T> {

    void accept(T t) throws Exception;
}
