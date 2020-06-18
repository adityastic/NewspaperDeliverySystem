package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.Customer;
import com.laqr.NewspaperDeliverySystem.repository.CustomerRepository;
import com.laqr.NewspaperDeliverySystem.repository.RouteRepository;
import com.laqr.NewspaperDeliverySystem.util.ListsHelper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer getCustomerById(Integer customerId) {
        return customerRepository.getOne(customerId);
    }

    public boolean ifCustomerNotPresent(Integer customerId, String fullName, String phoneNo, String address, List<Integer> subscriptions, Integer routeSelected, List<String> holidays) {
        Optional<Customer> customerFound = customerRepository.findTopByIdAndFullNameAndPhoneNoAndAddressAndRoute(
                customerId,
                fullName,
                Integer.parseInt(phoneNo),
                address,
                routeRepository.getOne(routeSelected));

        if (customerFound.isPresent()) {
            List<Integer> foundSubs = customerFound.get().getSubscription();
            List<String> foundHolidays = customerFound.get().getHoliday();

            return !ListsHelper.compareLists(foundSubs, subscriptions)
                    || !ListsHelper.compareLists(foundHolidays, holidays);
        }
        return true;
    }

    public void editCustomer(Integer customerId, String fullName, String phoneNo, String address, List<Integer> subscriptions, Integer routeSelected, List<String> holidays) {
        Customer customer = customerRepository.getOne(customerId);

        customer.setFullName(fullName);
        customer.setPhoneNo(Integer.parseInt(phoneNo));
        customer.setSubscription(subscriptions);
        customer.setRoute(routeRepository.getOne(routeSelected));
        customer.setAddress(address);
        customer.setHoliday(holidays);

        customerRepository.save(customer);
    }
}
