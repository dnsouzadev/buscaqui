package com.dnsouzadev.buscaqui.dtos.CategoryDtos;

import jakarta.validation.constraints.NotBlank;

public record SaveCategoryDto(@NotBlank String name) {
}
