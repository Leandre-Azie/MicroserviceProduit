package com.commerce.microcommerce.web.controller;

import java.net.URI;
import java.util.List;

import com.commerce.microcommerce.dao.ProductDao;
import com.commerce.microcommerce.model.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    // Récupérer la liste des produits
    // @GetMapping(value = "/Produits")
    // public List<Product> listProduct() {
    // return productDao.findAll();
    // }

    // Récupérer la liste des produits
    @GetMapping(value = "/Produits")
    public MappingJacksonValue otherListProduct() {
        List<Product> products = productDao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        FilterProvider listesDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamic", monFiltre);

        MappingJacksonValue produitsFiltres = new MappingJacksonValue(products);
        produitsFiltres.setFilters(listesDeNosFiltres);

        return produitsFiltres;
    }

    // Récupérer un produit à partir de son id
    @GetMapping(value = "/Produits/{id}")
    public Product getProduct(@PathVariable int id) {
        Product product = productDao.findById(id);
        return product;
    }

    // Ajouter un produit
    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> storeProduct(@RequestBody Product product) {
        Product productStored = productDao.save(product);

        if (productStored == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(productStored.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
