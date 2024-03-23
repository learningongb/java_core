package ru.gb.jcore.shop;

import ru.gb.jcore.shop.exceptions.CustomerException;
import ru.gb.jcore.shop.exceptions.ProductException;
import ru.gb.jcore.shop.exceptions.AmountException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Shop {
    private List<Client> clients;
    private List<Product> products;
    private List<Order> orders;

    public Shop() {
        clients = new ArrayList<>();
        products = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void init() {
        clients = new ArrayList<>(List.of(
                new Client("Петров Василий Иванович", LocalDate.of(2000, 10, 1), "1111111111", Client.Gender.MALE),
                new Client("Иванова Наталья Николаевна", LocalDate.of(2000, 10, 1), "+98888888", Client.Gender.FEMALE)
        ));

        products = new ArrayList<>(List.of(
                new Product("Ноутбук", 3000),
                new Product("Клавиатура", 200)
        ));
        orders = new ArrayList<>();
    }

    public Order createOrder(Client client) throws CustomerException {
        if (!clients.contains(client))
            throw new CustomerException("client " + client + " not found");
        Order order = new Order(client);
        orders.add(order);
        return order;
    }

    public Order createOrder(Client client, LocalDate date) throws CustomerException {
        if (!clients.contains(client))
            throw new CustomerException("client " + client + " not found");
        Order order = new Order(client, date);
        orders.add(order);
        return order;
    }

    public void addProductToOrder(int orderId, Product product, int quantity)
        throws ProductException, AmountException {
        if (!products.contains(product))
            throw new ProductException("product " + product + " not found");
        if (quantity < 0 || quantity > 100)
            throw new AmountException("quantity " + quantity + " is negative or too much");
        Order order = orders.stream().filter(order1 -> order1.getId() == orderId).findFirst().get();
        order.add(product, quantity);
    }

    public List<Client> getClients()
    {
        return clients;
    }

    public List<Product> getProducts() {
        return products;
    }

}
