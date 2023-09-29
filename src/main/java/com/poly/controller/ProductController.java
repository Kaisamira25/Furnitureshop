package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("")
    public String product() {
        return "product-page";
    }
    @GetMapping("/product-manage")
    public String productManage(){
        return "product-manage";
    }
}
