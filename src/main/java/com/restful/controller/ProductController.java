package com.restful.controller;

import com.restful.entity.Product;
import com.restful.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product postProduct(@RequestBody Product product) {
        return productService.postProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/p/{product}/o/{orderdBy}")
    public Product getByProductAndOrderdBy(@PathVariable("product") String product, @PathVariable("orderdBy") String orderdBy) {
        return productService.getByProductAndOrderdBy(product, orderdBy);
    }

    @GetMapping("/quantity/{quantity}")
    public List<Product> getQuantityMoreThan(@PathVariable("quantity") int quantity) {
        return productService.getQuantityMoreThan(quantity);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable UUID id) {
        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") UUID id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PatchMapping("/{id}")
    public Product patchProduct(@PathVariable("id") UUID id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable("id") UUID id) {
        productService.removeProduct(id);
    }
}
