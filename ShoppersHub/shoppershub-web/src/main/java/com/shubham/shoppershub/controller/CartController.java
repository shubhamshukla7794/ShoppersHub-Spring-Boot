package com.shubham.shoppershub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @RequestMapping({"/cart","/cart/index","/cart/index.html"})
    public String cartPage(){
        return "cart/index";
    }

}
