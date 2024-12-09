package com.dnsouzadev.buscaqui.services;

import com.dnsouzadev.buscaqui.models.CategoryModel;
import com.dnsouzadev.buscaqui.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryModel> getAllCategories() {
        return repository.findAll();
    }

    public CategoryModel saveCategory(CategoryModel category) {
        return repository.save(category);
    }
}
