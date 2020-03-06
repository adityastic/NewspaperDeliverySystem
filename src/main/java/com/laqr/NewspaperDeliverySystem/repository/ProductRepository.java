package com.laqr.NewspaperDeliverySystem.repository;

import com.laqr.NewspaperDeliverySystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findTopByName(String productName);
}
