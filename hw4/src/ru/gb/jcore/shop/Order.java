package ru.gb.jcore.shop;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
    public static int count = 0;
    private int id;
    private Client client;
    private Map<Product, Integer> products;
    private LocalDate date;
    private int sum;

    public Order(String line, Shop shop) {
        deserialize(line, shop);
        if (count < id)
            count = id;
    }

    public Order(Client client) {
        this(client, LocalDate.now());
    }

    public Order(Client client, LocalDate date) {
        this.id = ++count;
        this.client = client;
        this.products = new HashMap<>();
        this.date = date;
        this.sum = 0;
    }

    public int getId() {
        return id;
    }

    public void add(Product product, int quantity) {
        int productPrice = product.getPrice() - (product.getPrice() * getDiscount() / 100);
        products.put(product, quantity * productPrice);
        sum += quantity * productPrice;
    }

    private int getDiscount() {
        Holiday holiday = getHoliday(date);
        if (holiday == Holiday.NEWYEAR) {
            System.out.println("Назначена скидка 20%");
            return 20;
        }
        if (holiday == Holiday.FEBRUARY23 && client.gender == Client.Gender.MALE
                || holiday == Holiday.MARCH8 && client.gender == Client.Gender.FEMALE) {
            System.out.println("Назначена скидка 15%");
            return 15;
        }
        return 0;
    }

    private Holiday getHoliday(LocalDate date) {
        if (date.getDayOfMonth() == 8 && date.getMonthValue() == 3)
            return Holiday.MARCH8;
        else if (date.getDayOfMonth() == 23 && date.getMonthValue() == 2)
            return Holiday.FEBRUARY23;
        else if (date.getDayOfYear() <= 10 || date.getMonthValue() == 12 && date.getDayOfMonth() >= 30)
            return Holiday.NEWYEAR;
        return Holiday.NONE;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", products=" + products +
                ", date=" + date +
                ", sum=" + sum +
                '}';
    }

    public String serialize() {
        return id + ";"
                + client.getId() + ";"
                + date + ";"
                + sum
                + serializeProducts() + "\n";
    }

    private String serializeProducts() {
        String productsString = "";
        for (Product product: products.keySet()) {
            productsString = productsString + ";" + product.getId() + "~" + products.get(product);
        }
        return productsString;
    }

    public void deserialize(String line, Shop shop) {
        String[] parts = line.split(";");
        if (parts.length < 4)
            throw new RuntimeException("Ошибка формата строки");
        this.id = Integer.parseInt(parts[0]);
        this.client = shop.getClient(Integer.parseInt(parts[1]));
        this.date = LocalDate.parse(parts[2]);
        this.sum = Integer.parseInt(parts[3]);
        this.products = new HashMap<>();
        for (int i = 4; i < parts.length; i++) {
            String[] str = parts[i].split("~");
            Product pr = shop.getProduct(Integer.parseInt(str[0]));
            int ii = Integer.parseInt(str[1]);
            this.products.put(pr, ii);
        }
    }
}
