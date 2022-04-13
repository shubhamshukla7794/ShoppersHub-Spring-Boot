package com.shubham.shoppershub.dao;

import com.shubham.shoppershub.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
