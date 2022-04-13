package com.shubham.shoppershub.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "purchase_value")
    private Integer purchaseValue;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "payment_mode")
    private String paymentMode;
}
