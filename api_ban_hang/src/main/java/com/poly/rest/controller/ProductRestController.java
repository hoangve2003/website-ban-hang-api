package com.poly.rest.controller;

import com.poly.entity.Product;
import com.poly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("/rest/products/{id}")
    public Product getOne(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }
}
