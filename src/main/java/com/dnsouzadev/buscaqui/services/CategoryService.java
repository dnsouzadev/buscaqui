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
}
