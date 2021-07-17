package ru.job4j.stream;

public class Stock {
    public static void main(String[] args) {
        Goods goods = new Goods.Builder().buildName("shirt")
                .buildDescription("male shirt with print")
                .buildWeight("12.5 g")
                .buildPrice(75.99)
                .buildRemainder(30)
                .build();
        System.out.println(goods);
    }
}
