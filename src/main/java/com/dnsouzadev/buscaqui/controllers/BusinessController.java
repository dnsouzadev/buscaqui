package com.dnsouzadev.buscaqui.controllers;

import com.dnsouzadev.buscaqui.dtos.BusinessDtos.BusinessDto;
import com.dnsouzadev.buscaqui.dtos.BusinessDtos.SaveBusinessDto;
import com.dnsouzadev.buscaqui.models.BusinessModel;
import com.dnsouzadev.buscaqui.services.BusinessService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businesses")
public class BusinessController {

    @Autowired
    private BusinessService service;

    @GetMapping
    public List<BusinessDto> getAllBusinesses() {
        return service.getAllBusinesses();
    }

    @PostMapping
    public BusinessModel saveBusiness(@RequestBody @Valid SaveBusinessDto business) {
        return service.saveBusiness(business);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<BusinessDto> getBusinessByIdentifier(@PathVariable String identifier) {
        try {
            Long id = Long.valueOf(identifier);
            return ResponseEntity.ok(service.getBusinessById(id));
        } catch (NumberFormatException e) {
            BusinessDto business = service.getBusinessByName(identifier);
            if (business != null) {
                return ResponseEntity.ok(business);
            }
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusinessDto> updateBusiness(@PathVariable Long id, @RequestBody @Valid SaveBusinessDto business) {
        BusinessModel model = service.updateBusiness(id, business);
        if (model != null) {
            return ResponseEntity.ok(service.getBusinessById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long id) {
        if (service.deleteBusiness(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

