package ru.job4j.poly;

public class Bus implements Transport {

    @Override
    public void go() {
        System.out.println("Поехали!");
    }

    @Override
    public void passengers(int passenger) {
        System.out.println("Количество пассажиров в автобусе " + passenger);
    }

    @Override
    public double refuel(double fuel) {
       double price = 45.5;
       return price * fuel;
    }
}
