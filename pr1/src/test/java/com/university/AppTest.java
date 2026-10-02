package com.university;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {
    @Test
    public void cartShouldCalculateTotalPrice() {
        Category category = new Category(1, "Електроніка");
        Product laptop = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук", category);
        Product phone = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном", category);

        Cart cart = new Cart();
        cart.addProduct(laptop);
        cart.addProduct(phone);

        assertEquals(32999.49, cart.getTotalPrice(), 0.0001);

        Order order = new Order(cart);
        assertEquals(32999.49, order.getTotalPrice(), 0.0001);
    }
}
