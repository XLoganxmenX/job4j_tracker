package ru.job4j.stream;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public static void main(String[] args) {
        Stream.of(Suit.values())
                .flatMap(suits -> Stream.of(Value.values())
                        .map(values -> suits + " " + values))
                .forEach(System.out::println);
    }
}
