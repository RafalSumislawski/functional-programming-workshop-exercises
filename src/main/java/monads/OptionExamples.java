package monads;

import io.vavr.collection.HashMap;
import io.vavr.control.Option;

import java.util.*;

import static out.Print.*;

public class OptionExamples {

    public static void main(String[] args){

        int[] ints = {7, 77};
        println(ints[10]);

        List<Integer> list = new LinkedList<>();
        list.add(7);
        println(list.get(10));

        Map<Integer, Integer> map = new TreeMap<>();
        println(map.get(10));

        HashMap<Integer, Integer> numbers = HashMap.of(
                1, 3,
                2, 1,
                3, 2
        );

        Option<Integer> maybeResult = numbers.get(1)
                .flatMap(a -> numbers.get(a))
                .map(x -> x + 100);

        println(maybeResult.getOrElse(0));
        maybeResult.forEach(x -> println(x));
    }

}
