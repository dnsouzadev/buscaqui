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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private BusinessRepository businessRepository;

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {
        List<ProductModel> modelList = repository.findAll();
        return modelList.stream().map(ProductMapper::toDto).toList();
    }

    @Transactional
    public ProductModel saveProduct(SaveProductDto product) {
        BusinessModel business = businessRepository.findById(product.businessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));
        return repository.save(ProductMapper.toModel(product, business));
    }

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) {
        return repository.findById(id).map(ProductMapper::toDto).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional(readOnly = true)
    public ProductDto getProductByName(String name) {
        return repository.findByName(name).map(ProductMapper::toDto).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public ProductModel updateProduct(Long id, SaveProductDto product) {
        ProductModel productModel = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        BusinessModel business = businessRepository.findById(product.businessId())
                .orElseThrow(() -> new RuntimeException("Business not found"));
        productModel.setName(product.name());
        productModel.setPrice(product.price());
        productModel.setBusiness(business);
        return repository.save(productModel);
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}
