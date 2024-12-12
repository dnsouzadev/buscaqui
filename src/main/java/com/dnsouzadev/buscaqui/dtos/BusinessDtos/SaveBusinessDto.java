package com.dnsouzadev.buscaqui.dtos.BusinessDtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SaveBusinessDto(@NotBlank @Size(min = 3) String name, @NotBlank @Size(min = 10, max = 255) String address, @NotBlank @Size(min = 9, max = 14) String phone, Double deliveryFee, @NotBlank String category) {}
