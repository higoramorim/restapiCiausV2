package com.ciaus.restapiCiaus.repository;

import com.ciaus.restapiCiaus.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
