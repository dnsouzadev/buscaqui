package com.dnsouzadev.buscaqui.controllers;

import com.dnsouzadev.buscaqui.dtos.BusinessDtos.BusinessDto;
import com.dnsouzadev.buscaqui.dtos.CategoryDtos.CategoryDto;
import com.dnsouzadev.buscaqui.dtos.CategoryDtos.SaveCategoryDto;
import com.dnsouzadev.buscaqui.mapper.CategoryMapper;
import com.dnsouzadev.buscaqui.models.CategoryModel;
import com.dnsouzadev.buscaqui.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return service.getAllCategories();
    }

    @PostMapping
    public CategoryModel saveCategory(@RequestBody SaveCategoryDto category) {
        return service.saveCategory(category);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<CategoryDto> getCategoryByIdentifier(@PathVariable String identifier) {
        try {
            Long id = Long.valueOf(identifier);
            return ResponseEntity.ok(service.getCategoryById(id));
        } catch (NumberFormatException e) {
            CategoryDto category = service.getCategoryByName(identifier);
            if (category != null) {
                return ResponseEntity.ok(category);
            }
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody SaveCategoryDto category) {
        CategoryModel categoryModel = service.updateCategory(id, category);
        if (categoryModel != null) {
            return ResponseEntity.ok(CategoryMapper.toDto(categoryModel));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (service.deleteCategory(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

