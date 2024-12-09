package com.dnsouzadev.buscaqui.repositories;

import com.dnsouzadev.buscaqui.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
