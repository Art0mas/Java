package com.university;

import java.io.Console;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = System.console();
        Charset consoleCharset = console == null ? Charset.defaultCharset() : console.charset();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, consoleCharset));

        try (Scanner scanner = new Scanner(System.in, consoleCharset)) {
            ProductCatalog catalog = new ProductCatalog();
            User currentUser = new User("Андрій");

            Category electronics = new Category(1, "Електроніка");
            Category smartphones = new Category(2, "Смартфони");
            Category accessories = new Category(3, "Аксесуари");

            catalog.addProduct(
                    new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics));
            catalog.addProduct(new Product(2, "Смартфон", 12999.50,
                    "Смартфон з великим екраном та високою автономністю", smartphones));
            catalog.addProduct(new Product(3, "Навушники", 2499.00,
                    "Бездротові навушники з шумозаглушенням", accessories));

            while (true) {
                System.out.println("\n=== Головне Меню ===");
                System.out.println("1 - Переглянути список товарів");
                System.out.println("2 - Додати товар до кошика");
                System.out.println("3 - Переглянути кошик");
                System.out.println("4 - Зробити замовлення");
                System.out.println("5 - Видалити товар з кошика");
                System.out.println("6 - Переглянути історію замовлень");
                System.out.println("7 - Пошук товарів (за назвою або категорією)");
                System.out.println("0 - Вийти");

                int choice = readInt(scanner, "Виберіть опцію:");

                switch (choice) {
                    case 1:
                        catalog.printAllProducts();
                        break;
                    case 2:
                        int id = readInt(scanner, "Введіть ID товару для додавання до кошика:");
                        Product productToAdd = catalog.getProductById(id);
                        if (productToAdd != null) {
                            currentUser.getCart().addProduct(productToAdd);
                            System.out.println("Товар додано до кошика.");
                        } else {
                            System.out.println("Товар з таким ID не знайдено.");
                        }
                        break;
                    case 3:
                        System.out.println(currentUser.getCart());
                        break;
                    case 4:
                        if (currentUser.getCart().getProducts().isEmpty()) {
                            System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                        } else {
                            Order order = new Order(currentUser.getCart());
                            currentUser.addOrderToHistory(order);

                            System.out.println("Замовлення успішно оформлено!");
                            System.out.println(order);

                            currentUser.getCart().clear();
                        }
                        break;
                    case 5:
                        int removeId = readInt(scanner, "Введіть ID товару для видалення з кошика:");
                        Product productToRemove = catalog.getProductById(removeId);
                        if (productToRemove != null) {
                            currentUser.getCart().removeProduct(productToRemove);
                            System.out.println("Товар видалено з кошика (якщо він там був).");
                        } else {
                            System.out.println("Товар з таким ID не знайдено.");
                        }
                        break;
                    case 6:
                        currentUser.printOrderHistory();
                        break;
                    case 7:
                        System.out.print("Введіть назву товару або категорію для пошуку: ");
                        String query = scanner.nextLine();
                        catalog.searchProducts(query);
                        break;
                    case 0:
                        System.out.println("Дякуємо, що використовували наш магазин!");
                        return;
                    default:
                        System.out.println("Невідома опція. Спробуйте ще раз.");
                        break;
                }
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Некоректне значення. Введіть ціле число.");
            }
        }
    }
}