package com.shubham.shoppershub.dao;

import com.shubham.shoppershub.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
