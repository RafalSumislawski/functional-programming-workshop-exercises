package monads;

import io.vavr.control.Try;

import static out.Print.*;

public class TryExamples {

    public static void main(String[] arge){
        println(f("0"));
    }

    static Try<String> f(String number) {
        return Try.of(() -> Integer.parseInt(number))
                .flatMap(i -> Try.of(() -> 100 / i))
                .map(percent -> "1/" + number + "=" + percent + "%");
    }
}
