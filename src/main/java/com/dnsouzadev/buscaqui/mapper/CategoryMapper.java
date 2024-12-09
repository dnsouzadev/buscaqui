package com.dnsouzadev.buscaqui.mapper;

import com.dnsouzadev.buscaqui.dtos.CategoryDtos.SaveCategoryDto;
import com.dnsouzadev.buscaqui.models.CategoryModel;

public class CategoryMapper {

    public static CategoryModel toModel(SaveCategoryDto dto) {
        return new CategoryModel(dto.name());
    }
}
