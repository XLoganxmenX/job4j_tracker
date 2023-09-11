package ru.job4j.cast;

public class Main {
    public static void main(String[] args) {
        Vehicle bus = new Bus();
        Vehicle plane = new Plane();
        Vehicle train = new Train();
        Vehicle[] transport = {bus, plane, train};
        for (int i = 0; i < transport.length; i++) {
            transport[i].move();
        }
    }
}
