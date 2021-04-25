package ru.job4j.poly;

public class Bus implements Vehicle {

    @Override
    public void move() {
        System.out.println("Автобус едет по шоссе");
    }
}
