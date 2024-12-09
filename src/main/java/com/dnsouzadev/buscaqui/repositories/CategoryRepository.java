package com.dnsouzadev.buscaqui.repositories;

import com.dnsouzadev.buscaqui.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    Optional<CategoryModel> findByName(String category);
}
