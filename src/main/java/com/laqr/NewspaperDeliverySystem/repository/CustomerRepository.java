package com.laqr.NewspaperDeliverySystem.repository;

import com.laqr.NewspaperDeliverySystem.model.Customer;
import com.laqr.NewspaperDeliverySystem.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findTopByIdAndFullNameAndPhoneNoAndAddressAndRoute(Integer customerId, String fullName, Integer phoneNo, String address, Route route);
}
