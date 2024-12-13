package com.dnsouzadev.buscaqui.controllers;

import com.dnsouzadev.buscaqui.dtos.ProductDtos.ProductDto;
import com.dnsouzadev.buscaqui.dtos.ProductDtos.SaveProductDto;
import com.dnsouzadev.buscaqui.mapper.ProductMapper;
import com.dnsouzadev.buscaqui.models.ProductModel;
import com.dnsouzadev.buscaqui.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    final ProductService service;

    public ProductController (ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(service.getAllProducts(pageable));
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody @Valid SaveProductDto product) {
        ProductModel model = service.saveProduct(product);
        URI uri = URI.create("/products/" + model.getId());
        return ResponseEntity.created(uri).body(ProductMapper.toDto(model));
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
