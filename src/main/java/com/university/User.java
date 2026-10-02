package com.university;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private Cart cart;
    private List<Order> orderHistory;

    public User(String name) {
        this.name = name;
        this.cart = new Cart();
        this.orderHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cart getCart() {
        return cart;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    public void printOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("Історія замовлень порожня.");
        } else {
            System.out.println("--- Історія замовлень користувача " + name + " ---");
            for (int i = 0; i < orderHistory.size(); i++) {
                System.out.println("Замовлення #" + (i + 1) + ":");
                System.out.println(orderHistory.get(i));
                System.out.println("-------------------------");
            }
        }
    }
}