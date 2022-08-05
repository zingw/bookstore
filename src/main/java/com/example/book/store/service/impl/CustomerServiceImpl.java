package com.example.book.store.service.impl;

import com.example.book.store.entities.Customer;
import com.example.book.store.repository.CustomerRepository;
import com.example.book.store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public String addCustomer(Customer customer) {
        String phoneNumber = customer.getPhoneNumber();
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        if(customerOptional.isPresent()){
            return " Số điện thoại này đã tồn tại trên hệ thống, vui lòng kiểm tra lại";
        }
        else{
            customerRepository.save(customer);
            return " Thêm mới khách hàng thành công ";
        }
    }

    @Override
    public String updateCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
        if(customerOptional.isEmpty()){
            return " Số điện thoại này không đúng, vui lòng kiểm tra lại";
        }
        else customerRepository.save(customer);
        return "Cập nhật thông tin khách hàng thành công";
    }

    @Override
    public String removeCustomer(String phoneNumber) {
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        if(customerOptional.isEmpty()){
            return " Số điện thoại này không đúng, vui lòng kiểm tra lại";
        }
        else customerRepository.delete(customerOptional.get());
        return "Xóa khách hàng thành công";
    }

    @Override
    public Customer getCustomer(String phoneNumber) {
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        return customerOptional.orElse(null);
    }

    @Override
    public Boolean checkCustomer(String phoneNumber) {
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        return customerOptional.isPresent();
    }

    @Override
    public Page<Customer> getAllCustomer(Integer pageNo, Integer limit) {
        Pageable pageable = PageRequest.of(pageNo,limit);
        return customerRepository.findAll(pageable);
    }
}
