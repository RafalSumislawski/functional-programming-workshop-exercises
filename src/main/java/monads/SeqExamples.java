package monads;

import io.vavr.collection.Vector;

public class SeqExamples {

    public static void main(String[] args) {
        Vector.of(1, 2, 3)
                .flatMap(x -> Vector.of(x, x + 10))
                .filter(x -> x % 3 == 0)
                .forEach(System.out::println);
    }

}
