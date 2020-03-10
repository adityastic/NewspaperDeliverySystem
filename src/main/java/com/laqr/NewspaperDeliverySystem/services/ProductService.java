package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.*;
import com.laqr.NewspaperDeliverySystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProduct(String productName, ProductType productType, String frequency, Integer dayOfWeek, Double sellingCost, Double buyingCost) {
        Product newProduct = new Product(productName, productType, frequency, dayOfWeek, sellingCost, buyingCost);
        productRepository.save(newProduct);
    }

    public boolean checkProductName(String productName) {
        return productRepository.findTopByName(productName).isPresent();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(int productID) {
        productRepository.deleteById(productID);
    }
}
