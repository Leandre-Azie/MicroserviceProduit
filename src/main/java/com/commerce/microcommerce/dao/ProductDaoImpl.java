package com.commerce.microcommerce.dao;

import java.util.ArrayList;
import java.util.List;

import com.commerce.microcommerce.model.Product;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {

    public static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Ordinateur", 100, 150));
        products.add(new Product(2, "Aspirateur", 200, 300));
        products.add(new Product(3, "Télévision", 150, 300));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product save(Product product) {
        products.add(product);
        return product;
    }

}
