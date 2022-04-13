package com.shubham.shoppershub.dao;

import com.shubham.shoppershub.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
