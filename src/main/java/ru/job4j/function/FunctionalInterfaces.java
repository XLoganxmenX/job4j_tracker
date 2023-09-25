package ru.job4j.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        BiConsumer<Integer, String> biCon = (num, word) -> map.put(num, word);
        biCon.accept(1, "one");
        biCon.accept(2, "two");
        biCon.accept(3, "three");
        biCon.accept(4, "four");
        biCon.accept(5, "five");
        biCon.accept(6, "six");
        biCon.accept(7, "seven");

        BiPredicate<Integer, String> biPred = (i, s) -> i % 2 == 0 || s.length() == 4;
        map.forEach((key, value) -> {
                    if (biPred.test(key, value)) {
                        System.out.println("key: " + key + " value: " + value);
                    }
                }
        );

        Supplier<List<String>> sup = () -> new ArrayList<>(map.values());
        List<String> strings = new ArrayList<>(map.values());

        Consumer<String> con = (string) -> {
            System.out.println(string);
        };

        Function<String, String> func = (word) -> word.toUpperCase();
        for (String s : strings) {
            con.accept(func.apply(s));
        }
    }
}
