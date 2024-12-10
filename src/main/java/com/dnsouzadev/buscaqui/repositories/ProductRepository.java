package com.dnsouzadev.buscaqui.repositories;

import com.dnsouzadev.buscaqui.models.ProductModel;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByName(String name);
}
