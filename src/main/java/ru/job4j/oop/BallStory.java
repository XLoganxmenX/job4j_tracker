package ru.job4j.oop;

public class BallStory {
    public static void main(String[] args) {
        Ball kolobok = new Ball();
        Fox fox = new Fox();
        Hare hare = new Hare();
        Wolf wolf = new Wolf();

        hare.tryEat(kolobok);
        wolf.tryEat(kolobok);
        fox.tryEat(kolobok);
    }
}
