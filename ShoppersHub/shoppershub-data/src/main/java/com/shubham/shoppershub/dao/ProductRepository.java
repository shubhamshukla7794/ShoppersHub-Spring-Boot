package com.shubham.shoppershub.dao;

import com.shubham.shoppershub.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
