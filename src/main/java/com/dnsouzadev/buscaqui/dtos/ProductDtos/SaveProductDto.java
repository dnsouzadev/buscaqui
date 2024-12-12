package com.dnsouzadev.buscaqui.dtos.ProductDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SaveProductDto(@NotBlank @Size(min = 3, max = 100) String name, @NotBlank String description, @NotNull Double price, @NotNull Long businessId) {
}
