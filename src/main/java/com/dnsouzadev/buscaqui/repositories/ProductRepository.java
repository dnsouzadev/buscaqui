package com.dnsouzadev.buscaqui.repositories;

import com.dnsouzadev.buscaqui.models.ProductModel;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long>, PagingAndSortingRepository<ProductModel, Long> {
    Optional<ProductModel> findByName(String name);
}
