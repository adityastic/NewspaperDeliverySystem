package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.Customer;
import com.laqr.NewspaperDeliverySystem.repository.CustomerRepository;
import com.laqr.NewspaperDeliverySystem.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    final CustomerRepository customerRepository;
    final RouteRepository routeRepository;

    public CustomerService(CustomerRepository customerRepository, RouteRepository routeRepository) {
        this.customerRepository = customerRepository;
        this.routeRepository = routeRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addCustomer(String fullName, String phoneNo, String address, List<Integer> subscriptions, Integer routeSelected) {
        customerRepository.save(new Customer(fullName,
                address,
                Integer.parseInt(phoneNo),
                subscriptions,
                routeRepository.getOne(routeSelected),
                null));
    }
}
