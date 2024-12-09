package com.dnsouzadev.buscaqui.mapper;

import com.dnsouzadev.buscaqui.dtos.ProductDtos.ProductDto;
import com.dnsouzadev.buscaqui.dtos.ProductDtos.SaveProductDto;
import com.dnsouzadev.buscaqui.models.BusinessModel;
import com.dnsouzadev.buscaqui.models.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductModel toModel(SaveProductDto dto, BusinessModel business) {
        return new ProductModel(dto.name(), dto.description(), dto.price(), business);
    }

    public static ProductDto toDto(ProductModel model) {
        return new ProductDto(model.getName(), model.getDescription(), model.getPrice(), model.getBusiness().getDeliveryFee(), model.getBusiness().getName());
    }

}
