package ru.job4j.stream;

public class Goods {

    private String name;
    private String description;
    private String weight;
    private double price;
    private double remainder;

    @Override
    public String toString() {
        return "Goods{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + ", weight='" + weight + '\''
                + ", price=" + price
                + ", remainder=" + remainder
                + '}';
    }

    static class Builder {

        private String name;
        private String description;
        private String weight;
        private double price;
        private double remainder;

        Builder buildName(String name) {
            this.name = name;
            return this;
        }

        Builder buildDescription(String description) {
            this.description = description;
            return this;
        }

        Builder buildWeight(String weight) {
            this.weight = weight;
            return this;
        }

        Builder buildPrice(double price) {
            this.price = price;
            return this;
        }

        Builder buildRemainder(double remainder) {
            this.remainder = remainder;
            return this;
        }

        Goods build() {
            Goods goods = new Goods();
            goods.name = name;
            goods.description = description;
            goods.weight = weight;
            goods.price = price;
            goods.remainder = remainder;
            return goods;
        }
    }

}
