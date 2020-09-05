package com.commerce.microcommerce.dao;

import java.util.List;

import com.commerce.microcommerce.model.Product;

public interface ProductDao {
    public List<Product> findAll();

    public Product findById(int id);

    public Product save(Product product);
}
