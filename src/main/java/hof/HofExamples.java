package hof;

import io.vavr.Function1;
import io.vavr.collection.Vector;

import java.util.function.Consumer;

public class HofExamples {

    public static void main(String args[]) {
        Function1<Integer, Integer> addOne = x -> x + 1;
        Consumer<Integer> print = System.out::println;
        Vector.of(1, 2, 3).forEach(print);
        Function1<Integer, Integer> addSeven = addX(7);
    }

    static Function1<Integer, Integer> addX(int x) {
        return y -> y + x;
    }
}
