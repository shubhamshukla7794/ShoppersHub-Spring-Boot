package com.shubham.shoppershub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @RequestMapping({"","/","/index","/index.html"})
    public String displayProducts(){
        return "product/index";
    }

}
