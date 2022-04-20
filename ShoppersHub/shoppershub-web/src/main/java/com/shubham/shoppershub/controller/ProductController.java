package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.entity.Category;
import com.shubham.shoppershub.entity.Product;
import com.shubham.shoppershub.service.CategoryService;
import com.shubham.shoppershub.service.ProductService;
import com.shubham.shoppershub.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin/{adminId}/product")
public class ProductController {

    
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
//    private final String uploadDir = System.getProperty("user.dir")
//            + "/shoppershub-web/src/main/resources/static/productImages";
    private final String uploadDir = "./shoppershub-web/src/main/resources/static/productImages";

    public ProductController(ProductService productService, CategoryService categoryService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping({"","/","/index","/index.html"})
    public String displayProducts(@PathVariable Long adminId, Model model){
        model.addAttribute("admin", userService.getUserById(adminId));
        model.addAttribute("products", productService.getAllProducts());
        return "product/index";
    }

    @GetMapping("/add")
    public String addProduct(@PathVariable Long adminId, Model model) {
        Product newProduct = new Product();
        newProduct.setCategory(new Category());
        model.addAttribute("product", newProduct);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("admin", userService.getUserById(adminId));
        model.addAttribute("msg", "Add New Product");
        return "product/productForm";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam("productImage")MultipartFile file,
                              @RequestParam("imageName") String imageName,
                              @PathVariable Long adminId) throws IOException {

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
        return "redirect:/admin/"+adminId+"/product";
    }

    @GetMapping("/{productId}/update")
    public String updateProduct(@PathVariable Long adminId, @PathVariable Long productId, Model model) {
        Product product = productService.findProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("admin", userService.getUserById(adminId));
        model.addAttribute("msg", "Update Product Details");
        return "product/productForm";
    }

    @GetMapping("/{productId}/delete")
    public String deleteProduct(@PathVariable Long adminId, @PathVariable Long productId) {
        productService.deleteProductById(productId);
        return "redirect:/admin/"+adminId+"/product";
    }

}
