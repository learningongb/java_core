package ru.gb.jcore.shop;

public class Product {
    String caption;
    int price;

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "caption='" + caption + '\'' +
                '}';
    }

    public Product(String caption, int price) {
        this.caption = caption;
        this.price = price;
    }


}
