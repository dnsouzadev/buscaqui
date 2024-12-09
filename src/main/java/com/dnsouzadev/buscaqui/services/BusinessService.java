package com.dnsouzadev.buscaqui.services;

import com.dnsouzadev.buscaqui.dtos.BusinessDtos.BusinessDto;
import com.dnsouzadev.buscaqui.dtos.BusinessDtos.SaveBusinessDto;
import com.dnsouzadev.buscaqui.mapper.BusinessMapper;
import com.dnsouzadev.buscaqui.models.BusinessModel;
import com.dnsouzadev.buscaqui.models.CategoryModel;
import com.dnsouzadev.buscaqui.repositories.BusinessRepository;
import com.dnsouzadev.buscaqui.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;


    public List<BusinessDto> getAllBusinesses() {
        List<BusinessModel> modelList = repository.findAll();
        return modelList.stream().map(BusinessMapper::toDto).toList();
    }

    public BusinessModel saveBusiness(SaveBusinessDto business) {
        CategoryModel category = categoryRepository.findByName(business.category())
                .orElseThrow(() -> new RuntimeException("Category not found: " + business.category()));
        return repository.save(BusinessMapper.toModel(business, category));
    }

    public BusinessDto getBusinessById(Long id) {
        BusinessModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found: " + id));
        return BusinessMapper.toDto(model);
    }

    public BusinessDto getBusinessByName(String name) {
        BusinessModel business = repository.findByName(name);
        if (business == null) {
            throw new RuntimeException("Business not found with name: " + name);
        }
        return BusinessMapper.toDto(business);
    }
}
