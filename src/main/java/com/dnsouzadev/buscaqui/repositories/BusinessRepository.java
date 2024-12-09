package com.dnsouzadev.buscaqui.repositories;

import com.dnsouzadev.buscaqui.models.BusinessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessModel, Long> {
    BusinessModel findByName(String name);
}
