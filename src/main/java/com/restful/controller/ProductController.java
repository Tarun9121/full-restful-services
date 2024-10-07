package com.restful.controller;

import com.restful.entity.Product;
import com.restful.service.impl.ProductService;
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
    public List<Product> getByProductAndOrderdBy(@PathVariable("product") String product, @PathVariable("orderdBy") String orderdBy) {
        return productService.getByProductAndOrderdBy(product, orderdBy);
    }

    @GetMapping("/quantity/{quantity}")
    public List<Product> getQuantityMoreThan(@PathVariable("quantity") int quantity) {
        return productService.getQuantityMoreThan(quantity);
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable UUID productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") UUID productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @PatchMapping("/{productId}")
    public Product patchProduct(@PathVariable("productId") UUID productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public void removeProduct(@PathVariable("productId") UUID productId) {
        productService.removeProduct(productId);
    }
}
