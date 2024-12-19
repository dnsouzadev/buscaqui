package com.dnsouzadev.buscaqui.controllers;

import com.dnsouzadev.buscaqui.dtos.CategoryDtos.CategoryDto;
import com.dnsouzadev.buscaqui.dtos.CategoryDtos.SaveCategoryDto;
import com.dnsouzadev.buscaqui.mapper.CategoryMapper;
import com.dnsouzadev.buscaqui.models.CategoryModel;
import com.dnsouzadev.buscaqui.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> getAllCategories(Pageable pageable) {
        return ResponseEntity.ok(service.getAllCategories(pageable));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid SaveCategoryDto category) {
        CategoryModel model = service.saveCategory(category);
        URI uri = URI.create("/categories/" + model.getId());
        return ResponseEntity.created(uri).body(CategoryMapper.toDto(model));
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
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody @Valid SaveCategoryDto category) {
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

