package ru.job4j.poly;

public class City {
    public static void main(String[] args) {
        Plain plain = new Plain();
        Bus bus = new Bus();
        Train train = new Train();

        Vehicle[] vehicle = {plain, bus, train};
        for (int i = 0; i < vehicle.length; i++) {
            vehicle[i].move();
        }
    }
}
