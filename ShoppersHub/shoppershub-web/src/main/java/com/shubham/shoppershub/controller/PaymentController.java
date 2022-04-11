package com.shubham.shoppershub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {

    @RequestMapping({"/payment","/payment/index","/payment/index.html"})
    public String paymentPage() {
        return "payment/index";
    }

}
