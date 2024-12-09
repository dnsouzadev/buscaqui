package com.dnsouzadev.buscaqui.controllers;

import com.dnsouzadev.buscaqui.models.BusinessModel;
import com.dnsouzadev.buscaqui.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {

    @Autowired
    private BusinessService service;

    @GetMapping
    public List<BusinessModel> getAllBusinesses() {
        return service.getAllBusinesses();
    }

    @PostMapping
    public BusinessModel saveBusiness(@RequestBody BusinessModel business) {
        return service.saveBusiness(business);
    }
}

