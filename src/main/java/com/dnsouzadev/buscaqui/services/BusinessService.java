package com.dnsouzadev.buscaqui.services;

import com.dnsouzadev.buscaqui.dtos.BusinessDtos.BusinessDto;
import com.dnsouzadev.buscaqui.dtos.BusinessDtos.SaveBusinessDto;
import com.dnsouzadev.buscaqui.mapper.BusinessMapper;
import com.dnsouzadev.buscaqui.models.BusinessModel;
import com.dnsouzadev.buscaqui.models.CategoryModel;
import com.dnsouzadev.buscaqui.repositories.BusinessRepository;
import com.dnsouzadev.buscaqui.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Transactional(readOnly = true)
    public Page<BusinessDto> getAllBusinesses(Pageable pageable) {
        return repository.findAll(pageable).map(BusinessMapper::toDto);
    }

    @Transactional
    public BusinessModel saveBusiness(SaveBusinessDto business) {
        CategoryModel category = categoryRepository.findByName(business.category())
                .orElseThrow(() -> new RuntimeException("Category not found: " + business.category()));
        return repository.save(BusinessMapper.toModel(business, category));
    }

    @Transactional(readOnly = true)
    public BusinessDto getBusinessById(Long id) {
        BusinessModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found: " + id));
        return BusinessMapper.toDto(model);
    }

    @Transactional(readOnly = true)
    public BusinessDto getBusinessByName(String name) {
        BusinessModel business = repository.findByName(name);
        if (business == null) {
            throw new RuntimeException("Business not found with name: " + name);
        }
        return BusinessMapper.toDto(business);
    }

    @Transactional
    public BusinessModel updateBusiness(Long id, SaveBusinessDto business) {
        CategoryModel category = categoryRepository.findByName(business.category())
                .orElseThrow(() -> new RuntimeException("Category not found: " + business.category()));
        BusinessModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found: " + id));
        model.setName(business.name());
        model.setAddress(business.address());
        model.setPhone(business.phone());
        model.setDeliveryFee(business.deliveryFee());
        model.setCategory(category);
        return repository.save(model);
    }

    @Transactional
    public boolean deleteBusiness(Long id) {
        BusinessModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found: " + id));
        repository.delete(model);
        return true;
    }
}
