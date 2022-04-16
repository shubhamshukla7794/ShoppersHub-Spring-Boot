package com.shubham.shoppershub.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

//    @Column(name = "category_id")
//    private Long categoryId;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock")
    private Integer stock;

    @Lob
    @Column(name = "product_description")
    private String productDesc;

//    @Lob
//    private Byte[] image;

    @Column(name = "product_image")
    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
}
