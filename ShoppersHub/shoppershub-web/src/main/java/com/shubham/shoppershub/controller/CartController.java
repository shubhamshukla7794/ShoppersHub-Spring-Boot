package com.shubham.shoppershub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

    @RequestMapping({"","/","/index","/index.html"})
    public String cartPage(){
        return "cart/index";
    }

}
