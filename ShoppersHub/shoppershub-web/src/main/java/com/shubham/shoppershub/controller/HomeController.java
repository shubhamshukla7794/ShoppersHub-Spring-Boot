package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.service.CategoryService;
import com.shubham.shoppershub.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public HomeController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @RequestMapping({"","/","index","index.html"})
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("products", productService.findRandomProducts());
        return "index";
    }

    @GetMapping("/shop/category/{categoryId}")
    public String shopByCategoryId(@PathVariable Long categoryId, Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("products", productService.getAllProductsByCategoryId(categoryId));
        return "product/shopProduct";
    }

    @GetMapping("/shop/viewproduct/{productId}")
    public String viewProductDetails(@PathVariable Long productId, Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("product", productService.findProductById(productId));
        return "product/viewProduct";
    }
}
