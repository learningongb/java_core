package ru.gb.jcore.shop;

public class Product {
    static int maxId = 0;
    int id;
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
    public Product(String line) {
        deserialize(line);
        if (maxId < id)
            maxId = id;
    }

    public Product(String caption, int price) {
        this.caption = caption;
        this.price = price;
        this.id = ++maxId;
    }

    public String serialize() {
        return id + ";" + caption + ";" + price + "\n";
    }

    public int getId() {
        return id;
    }

    public void deserialize(String line) {
        String[] parts = line.split(";");
        if (parts.length != 3)
            throw new RuntimeException("Ошибка формата строки");
        this.id = Integer.parseInt(parts[0]);
        this.caption = parts[1];
        this.price = Integer.parseInt(parts[2]);
    }
}
