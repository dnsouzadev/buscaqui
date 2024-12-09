package com.dnsouzadev.buscaqui.mapper;

import com.dnsouzadev.buscaqui.dtos.CategoryDtos.CategoryDto;
import com.dnsouzadev.buscaqui.dtos.CategoryDtos.SaveCategoryDto;
import com.dnsouzadev.buscaqui.models.CategoryModel;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryModel toModel(SaveCategoryDto dto) {
        return new CategoryModel(dto.name());
    }

    public static CategoryDto toDto(CategoryModel category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
