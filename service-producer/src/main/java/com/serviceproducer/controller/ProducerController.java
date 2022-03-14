package com.serviceproducer.controller;

import com.serviceproducer.entity.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/v1")
public class ProducerController {
    @GetMapping("/products")
    public Product createProduct(){
        Product product=new Product();
        product.setName("Prod 1");
        product.setPrice("1000");
        return product;
    }
}
