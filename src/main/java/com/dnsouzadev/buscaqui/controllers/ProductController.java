package com.dnsouzadev.buscaqui.controllers;

import com.dnsouzadev.buscaqui.dtos.CategoryDtos.CategoryDto;
import com.dnsouzadev.buscaqui.dtos.CategoryDtos.SaveCategoryDto;
import com.dnsouzadev.buscaqui.dtos.ProductDtos.ProductDto;
import com.dnsouzadev.buscaqui.dtos.ProductDtos.SaveProductDto;
import com.dnsouzadev.buscaqui.mapper.CategoryMapper;
import com.dnsouzadev.buscaqui.models.CategoryModel;
import com.dnsouzadev.buscaqui.models.ProductModel;
import com.dnsouzadev.buscaqui.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return service.getAllProducts(pageable);
    }

    @PostMapping
    public ProductModel saveProduct(@RequestBody @Valid SaveProductDto product) {
        return service.saveProduct(product);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<ProductDto> getProductByIdentifier(@PathVariable String identifier) {
        try {
            Long id = Long.valueOf(identifier);
            return ResponseEntity.ok(service.getProductById(id));
        } catch (NumberFormatException e) {
            ProductDto product = service.getProductByName(identifier);
            if (product != null) {
                return ResponseEntity.ok(product);
            }
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody @Valid SaveProductDto product) {
        return ResponseEntity.ok(service.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (service.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
