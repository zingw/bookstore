package com.example.book.store.service;

import com.example.book.store.entities.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {
    String addCustomer(Customer customer);

    String updateCustomer(Customer customer);

    String removeCustomer(String phoneNumber);

    Customer getCustomer(String phoneNumber);

    Boolean checkCustomer(String phoneNumber);

    Page<Customer> getAllCustomer(Integer pageNo, Integer limit);
}
