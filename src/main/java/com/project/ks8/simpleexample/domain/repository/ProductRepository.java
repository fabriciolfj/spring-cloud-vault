package com.project.ks8.simpleexample.domain.repository;

import com.project.ks8.simpleexample.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
