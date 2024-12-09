package com.dnsouzadev.buscaqui.services;

import com.dnsouzadev.buscaqui.models.BusinessModel;
import com.dnsouzadev.buscaqui.repositories.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository repository;


    public List<BusinessModel> getAllBusinesses() {
        return repository.findAll();
    }

    public BusinessModel saveBusiness(BusinessModel business) {
        return repository.save(business);
    }
}
