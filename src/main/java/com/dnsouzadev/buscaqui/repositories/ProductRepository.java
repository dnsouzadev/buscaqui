package com.dnsouzadev.buscaqui.repositories;

import com.dnsouzadev.buscaqui.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
