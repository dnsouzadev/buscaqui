package com.dnsouzadev.buscaqui.mapper;

import com.dnsouzadev.buscaqui.dtos.BusinessDtos.BusinessDto;
import com.dnsouzadev.buscaqui.dtos.BusinessDtos.SaveBusinessDto;
import com.dnsouzadev.buscaqui.models.BusinessModel;
import com.dnsouzadev.buscaqui.models.CategoryModel;
import com.dnsouzadev.buscaqui.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessMapper {

    public static BusinessModel toModel(SaveBusinessDto dto, CategoryModel category) {
        return new BusinessModel(dto.name(), dto.address(), dto.phone(), dto.deliveryFee(), category);
    }

    public static BusinessDto toDto(BusinessModel model) {
        return new BusinessDto(model.getId(), model.getName(), model.getAddress(), model.getPhone(), model.getDeliveryFee(), model.getCategory().getName());
    }

}
