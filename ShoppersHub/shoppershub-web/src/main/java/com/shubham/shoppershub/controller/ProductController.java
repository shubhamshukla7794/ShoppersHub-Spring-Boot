package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.entity.Category;
import com.shubham.shoppershub.entity.Product;
import com.shubham.shoppershub.service.CategoryService;
import com.shubham.shoppershub.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    
    private final ProductService productService;
    private final CategoryService categoryService;
//    private final String uploadDir = System.getProperty("user.dir")
//            + "/shoppershub-web/src/main/resources/static/productImages";
    private final String uploadDir = "./shoppershub-web/src/main/resources/static/productImages";

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping({"","/","/index","/index.html"})
    public String displayProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "product/index";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        Product newProduct = new Product();
        newProduct.setCategory(new Category());
        model.addAttribute("product", newProduct);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "product/productForm";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam("productImage")MultipartFile file,
                              @RequestParam("imageName") String imageName) throws IOException {

        String imageUUID, temp, extension;
        if (!file.isEmpty()) {
            temp = StringUtils.cleanPath(file.getOriginalFilename());
            extension = StringUtils.getFilenameExtension(temp);
//            imageUUID = file.getOriginalFilename();
            imageUUID = product.getProductName().replaceAll("[#%&{}$!\'\":@\\<>|*?/=`+]", "")
                            +"." + extension;
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imageName;
        }
        product.setImageName(imageUUID);

        productService.saveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/{productId}/update")
    public String updateProduct(@PathVariable String productId, Model model) {
        Product product = productService.findProductById(Long.valueOf(productId));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "product/productForm";
    }

}
