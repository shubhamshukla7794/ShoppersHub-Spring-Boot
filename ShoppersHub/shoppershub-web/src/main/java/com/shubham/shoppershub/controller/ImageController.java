package com.shubham.shoppershub.controller;

import com.shubham.shoppershub.entity.Product;
import com.shubham.shoppershub.service.ProductService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Controller
public class ImageController {

    private final ProductService productService;
    private final String uploadDir = "./shoppershub-web/src/main/resources/static/productImages/";

    public ImageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}/productImage")
    public void renderImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        Product product = productService.findProductById(Long.valueOf(id));
        if (product.getImageName() != null) {
            if (Objects.equals(StringUtils.getFilenameExtension(product.getImageName()), "png")) {
                response.setContentType("image/png");
            } else {
                response.setContentType("image/jpeg");
            }
            InputStream inputStream = new FileInputStream(new File(uploadDir+product.getImageName()));
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }
}
