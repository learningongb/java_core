package ru.gb.jcore.shop;

import ru.gb.jcore.shop.exceptions.CustomerException;
import ru.gb.jcore.shop.exceptions.ProductException;
import ru.gb.jcore.shop.exceptions.AmountException;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Client> clients;
    private List<Product> products;
    private List<Order> orders;

    public Shop() {
        clients = new ArrayList<>();
        products = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void clear() {
        clients = new ArrayList<>();
        products = new ArrayList<>();
        orders = new ArrayList<>();
        System.out.println("*************  Данные магазина очищены  *****************");
    }

    public void status() {
        System.out.println("*************  Данные по магазину  *****************");
        System.out.println("Количество клиентов " + clients.size());
        System.out.println("Количество продуктов " + products.size());
        System.out.println("Количество заказов " + orders.size());
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

    public void dump() {
        boolean hasError = false;
        boolean hasSuccess = false;
        System.out.println("Сохранение данных магазина");
        dumpClients(".//Clients", hasError, hasSuccess);
        dumpProducts(".//Products", hasError, hasSuccess);
        dumpOrders(".//Orders", hasError, hasSuccess);

        if (hasSuccess)
            if (hasError)
                System.out.println("Данные сохранены с ошибками");
            else
                System.out.println("Данные сохранены");
        else
            System.out.println("Ошибка при сохранении данных");

    }

    private void dumpClients(String filename, boolean hasError, boolean hasSuccess) {
        try (FileOutputStream foStream = new FileOutputStream(filename)) {
            for (Client client : clients) {
                foStream.write(client.serialize().getBytes());
            }
            foStream.flush();
            hasSuccess = true;
        } catch (Exception e) {
            System.out.println("Ошибка при записи клиентов:");
            System.out.println(e.getMessage());
            hasError = true;
        }

    }

    private void dumpProducts(String filename, boolean hasError, boolean hasSuccess) {
        try (FileOutputStream foStream = new FileOutputStream(filename)) {
            for (Product product : products) {
                foStream.write(product.serialize().getBytes());
            }
            foStream.flush();
            hasSuccess = true;
        } catch (Exception e) {
            System.out.println("Ошибка при записи товаров:");
            System.out.println(e.getMessage());
            hasError = true;
        }
    }

    private void dumpOrders(String filename, boolean hasError, boolean hasSuccess) {
        try (FileOutputStream foStream = new FileOutputStream(filename)) {
            for (Order order : orders) {
                foStream.write(order.serialize().getBytes());
            }
            foStream.flush();
            hasSuccess = true;
        } catch (Exception e) {
            System.out.println("Ошибка при записи заказов:");
            System.out.println(e.getMessage());
            hasError = true;
        }
    }

    public void restore() {
        boolean success = true;
        System.out.println("Восстановление данных магазина");
        success = restoreClients(".//Clients") && success;
        success = restoreProducts(".//Products") && success;
        success = restoreOrders(".//Orders") && success;

        if (success)
            System.out.println("Данные восстановлены");
        else
            System.out.println("Ошибка при восстановлении данных");
    }

    private boolean restoreClients(String filename) {
        try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                clients.add(new Client(line));
            }
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при чтении клиентов:");
            System.out.println(e.getMessage());
        }
        return false;
    }

    private boolean restoreProducts(String filename) {
        try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                products.add(new Product(line));
            }
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при чтении товаров:");
            System.out.println(e.getMessage());
        }
        return false;
    }

    private boolean restoreOrders(String filename) {
        try (FileReader fr = new FileReader(filename); BufferedReader br = new BufferedReader(fr)) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                orders.add(new Order(line, this));
            }
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при чтении товаров:");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Product getProduct(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().get();
    }

    public Client getClient(int id) {
        return clients.stream().filter(client -> client.getId() == id).findFirst().get();
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
