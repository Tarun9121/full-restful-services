package com.restful.service;

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

    public Product getProduct(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("product not found with id: " + id));
    }

    public Product getByProductAndOrderdBy(String product, String orderdBy) {
        return productRepository.findByProductNameAndOrderdBy(product, orderdBy);
    }

    public Product updateProduct(UUID id, Product product) {
        Product existingProduct = getProduct(id);
        return productRepository.save(product);
    }

    public void removeProduct(UUID id) {
        Product existingData = getProduct(id);
    }

    public List<Product> getQuantityMoreThan(int quantity) {
        return productRepository.getQuantityMoreThan(quantity);
    }
}
