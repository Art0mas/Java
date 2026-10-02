package com.university;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    private List<Product> allProducts;

    public ProductCatalog() {
        this.allProducts = new ArrayList<>();
    }

    public void addProduct(Product product) {
        allProducts.add(product);
    }

    public void printAllProducts() {
        if (allProducts.isEmpty()) {
            System.out.println("Каталог товарів порожній.");
            return;
        }
        for (Product p : allProducts) {
            System.out.println(p);
        }
    }

    public Product getProductById(int id) {
        for (Product p : allProducts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void searchProducts(String query) {
        System.out.println("--- Результати пошуку для '" + query + "' ---");
        boolean found = false;
        String lowerQuery = query.toLowerCase();

        for (Product p : allProducts) {
            if (p.getName().toLowerCase().contains(lowerQuery) ||
                    (p.getCategory() != null && p.getCategory().getName().toLowerCase().contains(lowerQuery))) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Товарів не знайдено.");
        }
    }
}