package com.dnsouzadev.buscaqui.services;

import com.dnsouzadev.buscaqui.dtos.ProductDtos.ProductDto;
import com.dnsouzadev.buscaqui.dtos.ProductDtos.SaveProductDto;
import com.dnsouzadev.buscaqui.mapper.ProductMapper;
import com.dnsouzadev.buscaqui.models.BusinessModel;
import com.dnsouzadev.buscaqui.models.ProductModel;
import com.dnsouzadev.buscaqui.repositories.BusinessRepository;
import com.dnsouzadev.buscaqui.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private BusinessRepository businessRepository;

    public List<ProductDto> getAllProducts() {
        List<ProductModel> modelList = repository.findAll();
        return modelList.stream().map(ProductMapper::toDto).toList();
    }

    public ProductModel saveProduct(SaveProductDto product) {
        BusinessModel business = businessRepository.findById(product.businessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));
        return repository.save(ProductMapper.toModel(product, business));
    }
}
