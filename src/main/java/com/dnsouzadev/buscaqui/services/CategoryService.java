package com.dnsouzadev.buscaqui.services;

import com.dnsouzadev.buscaqui.dtos.CategoryDtos.SaveCategoryDto;
import com.dnsouzadev.buscaqui.dtos.CategoryDtos.CategoryDto;
import com.dnsouzadev.buscaqui.mapper.CategoryMapper;
import com.dnsouzadev.buscaqui.models.CategoryModel;
import com.dnsouzadev.buscaqui.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryDto> getAllCategories() {
        List<CategoryModel> listCategories = repository.findAll();
        return listCategories.stream().map(CategoryMapper::toDto).toList();
    }

    public CategoryModel saveCategory(SaveCategoryDto category) {
        return repository.save(CategoryMapper.toModel(category));
    }

    public CategoryDto getCategoryById(Long id) {
        return repository.findById(id).map(CategoryMapper::toDto).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public CategoryDto getCategoryByName(String name) {
        return repository.findByName(name).map(CategoryMapper::toDto).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public CategoryModel updateCategory(Long id, SaveCategoryDto category) {
        CategoryModel categoryModel = repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryModel.setName(category.name());
        return repository.save(categoryModel);
    }

    public boolean deleteCategory(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
