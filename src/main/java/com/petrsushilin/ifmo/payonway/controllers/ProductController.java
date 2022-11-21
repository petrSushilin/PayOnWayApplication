package com.petrsushilin.ifmo.payonway.controllers;

import com.petrsushilin.ifmo.payonway.entity.Product;
import com.petrsushilin.ifmo.payonway.entity.nums.ProductType;
import com.petrsushilin.ifmo.payonway.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "product")
public class ProductController {
    @PostMapping(path = "/add")
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }

    @GetMapping(path = "/get/id={productId}")
    public Product getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @PutMapping(path = "/change/id={productId}/fields")
    public void changeProductFields(@PathVariable Long productId,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) ProductType type,
                                    @RequestParam(required = false) double cost) {
        productService.changeProductFields(productId, name, type, cost);
    }

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
