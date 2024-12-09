package com.dnsouzadev.buscaqui.controllers;

import com.dnsouzadev.buscaqui.models.ProductModel;
import com.dnsouzadev.buscaqui.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<ProductModel> getAllProducts() {
        return service.getAllProducts();
    }

    @PostMapping
    public ProductModel saveProduct(@RequestBody ProductModel product) {
        return service.saveProduct(product);
    }
}
