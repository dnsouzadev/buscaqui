package com.dnsouzadev.buscaqui.controllers;

import com.dnsouzadev.buscaqui.models.CategoryModel;
import com.dnsouzadev.buscaqui.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<CategoryModel> getAllCategories() {
        return service.getAllCategories();
    }

    @PostMapping
    public CategoryModel saveCategory(@RequestBody CategoryModel category) {
        return service.saveCategory(category);
    }
}

