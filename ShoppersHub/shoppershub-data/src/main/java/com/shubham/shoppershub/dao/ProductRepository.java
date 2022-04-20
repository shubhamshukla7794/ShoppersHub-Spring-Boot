package com.shubham.shoppershub.dao;

import com.shubham.shoppershub.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.id = ?1")
    public List<Product> findAllByCategoryId(Long id);

    @Query(value="SELECT * FROM `shoppershub`.`product` ORDER BY rand() LIMIT 9", nativeQuery=true)
    public List<Product> findRandomProducts();
}
