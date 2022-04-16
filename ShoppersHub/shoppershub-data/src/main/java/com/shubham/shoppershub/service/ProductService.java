package com.shubham.shoppershub.service;

import com.shubham.shoppershub.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public void saveProduct(Product product);
    public void deleteProductById(Long id);
    public List<Product> getAllProductsByCategoryId(Long categoryId);
    public Product findProductById(Long id);
}
