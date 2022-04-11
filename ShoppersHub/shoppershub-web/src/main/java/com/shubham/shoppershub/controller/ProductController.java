package com.shubham.shoppershub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    @RequestMapping({"/product","/product/index","/product/index.html"})
    public String displayProducts(){
        return "product/index";
    }

}
