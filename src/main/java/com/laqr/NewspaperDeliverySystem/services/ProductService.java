package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.Product;
import com.laqr.NewspaperDeliverySystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public boolean checkProductName(String productName) {
        return productRepository.findTopByName(productName).isPresent();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
