package com.shubham.shoppershub.service;

import com.shubham.shoppershub.dao.ProductRepository;
import com.shubham.shoppershub.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    //System.out.println();
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        product.setCategory(categoryService.findCategoryById(product.getCategory().getId()));
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProductsByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Product findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("Product with id "+id+" not found!!!");
        }

        return productOptional.get();
    }

    @Override
    public List<Product> findRandomProducts() {
        return productRepository.findRandomProducts();
    }
}
