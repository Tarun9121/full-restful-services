package com.restful.service.impl;

import com.restful.entity.Product;
import com.restful.exception.ProductNotFoundException;
import com.restful.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product postProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("product not found with id: " + productId));
    }

    public List<Product> getByProductAndOrderdBy(String product, String orderdBy) {
        return productRepository.findByProductNameAndOrderdBy(product, orderdBy);
    }

    public Product updateProduct(UUID productId, Product product) {
        Product existingProduct = getProductById(productId);
        product.setId(productId);
        return productRepository.save(product);
    }

    public void removeProduct(UUID productId) {
        Product existingData = getProductById(productId);
    }

    public List<Product> getQuantityMoreThan(int quantity) {
        return productRepository.getQuantityMoreThan(quantity);
    }
}
