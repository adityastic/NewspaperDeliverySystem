package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.Product;
import com.laqr.NewspaperDeliverySystem.model.ProductFrequency;
import com.laqr.NewspaperDeliverySystem.model.ProductType;
import com.laqr.NewspaperDeliverySystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProduct(String productName, ProductType productType, ProductFrequency frequency, Integer dayOfWeek, Double sellingCost, Double buyingCost) {
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

    public Product getProductById(Integer productID) {
        return productRepository.findById(productID).get();
    }

    public void editProduct(Integer productID, String productName, ProductType productType, ProductFrequency frequency, Integer dayOfWeek, Double sellingCost, Double buyingCost) {
        Product foundProduct = productRepository.findById(productID).get();
        foundProduct.setName(productName);
        foundProduct.setType(productType);
        foundProduct.setFrequency(frequency);
        foundProduct.setDayOfWeek(dayOfWeek);
        foundProduct.setSellingCost(sellingCost);
        foundProduct.setBuyingCost(buyingCost);
        productRepository.save(foundProduct);
    }

    public boolean checkNotThisProductName(Integer productID, String productName) {
        Optional<Product> maybeUser = productRepository.findTopByName(productName);
        return maybeUser.isPresent() && maybeUser.get().getId() != productID;
    }
}
