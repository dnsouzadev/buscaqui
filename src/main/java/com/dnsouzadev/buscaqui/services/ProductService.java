package com.dnsouzadev.buscaqui.services;

import com.dnsouzadev.buscaqui.models.ProductModel;
import com.dnsouzadev.buscaqui.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductModel> getAllProducts() {
        return repository.findAll();
    }

    public ProductModel saveProduct(ProductModel product) {
        return repository.save(product);
    }
}
