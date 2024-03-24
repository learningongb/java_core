import ru.gb.jcore.shop.Client;
import ru.gb.jcore.shop.Order;
import ru.gb.jcore.shop.Shop;
import ru.gb.jcore.shop.Product;
import ru.gb.jcore.shop.exceptions.AmountException;
import ru.gb.jcore.shop.exceptions.CustomerException;
import ru.gb.jcore.shop.exceptions.ProductException;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Shop shop = new Shop();
        shop.init();

        Client client = shop.getClients().get(0);
        System.out.println("*****Существующий клиент*********");
        try {
            System.out.println("Создание заказа на текущую дату");
            Order order1 = shop.createOrder(client);
            System.out.println(order1);
            System.out.println("Добавление существующего товара");
            addToOrder(shop, order1, shop.getProducts().get(0), 5);
            System.out.println(order1);
            System.out.println("Добавление существующего товара - отрицательное количество");
            addToOrder(shop, order1, shop.getProducts().get(0), -5);
            System.out.println(order1);
            System.out.println("Добавление существующего товара - большое количество");
            addToOrder(shop, order1, shop.getProducts().get(0), 150);
            System.out.println(order1);
            System.out.println("Добавление не существующего товара");
            addToOrder(shop, order1, new Product("Новый продукт", 150), 150);
            System.out.println(order1);
            System.out.println("Добавление другого существующего товара");
            addToOrder(shop, order1, shop.getProducts().get(1), 5);
            System.out.println(order1);
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*****Несуществующий клиент*********");
        try {
            Order order2 = shop.createOrder(new Client("Gerg", LocalDate.of(2010, 1,1), "3333333", Client.Gender.MALE));
            System.out.println(order2);
            order2.add(shop.getProducts().get(0), 5);
            System.out.println(order2);
        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("*****Проверка скидки*********");
        try {
            System.out.println("Создание заказа на Новый год");
            Order order3 = shop.createOrder(client, LocalDate.of(2024, 1, 1));
            addToOrder(shop, order3, shop.getProducts().get(0), 5);
            System.out.println(order3);

            System.out.println("Создание заказа на 8 марта - заказчик женщина");
            Order order4 = shop.createOrder(shop.getClients().get(1), LocalDate.of(2024, 3, 8));
            addToOrder(shop, order4, shop.getProducts().get(0), 5);
            System.out.println(order4);

            System.out.println("Создание заказа на 8 марта - заказчик мужчина");
            Order order5 = shop.createOrder(shop.getClients().get(0), LocalDate.of(2024, 3, 8));
            addToOrder(shop, order5, shop.getProducts().get(0), 5);
            System.out.println(order5);

        } catch (CustomerException e) {
            System.out.println(e.getMessage());
        }

        shop.status();
        shop.dump();

        shop.clear();
        shop.status();

        shop.restore();
        shop.status();

    }

    private static void addToOrder(Shop shop, Order order, Product product, int quantity) {
        try {
            shop.addProductToOrder(order.getId(), product, quantity);
        } catch (AmountException e) {
            System.out.println("Уточните количество");
            System.out.println(e.getMessage());
        } catch (ProductException e)
        {
            System.out.println("Уточните товар");
            System.out.println(e.getMessage());
        }
    }
}
