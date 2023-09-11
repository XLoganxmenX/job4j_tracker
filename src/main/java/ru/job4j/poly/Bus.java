package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Едем");
    }

    @Override
    public void passengers(int passengers) {
        System.out.printf("У нас есть %s пассажиров\n", passengers);
    }

    @Override
    public int reFuel(int fuel) {
        return fuel * 72;
    }
}
