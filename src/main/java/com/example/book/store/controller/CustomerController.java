package com.example.book.store.controller;

import com.example.book.store.entities.Customer;
import com.example.book.store.service.CustomerService;
import com.example.book.store.constants.CustomerAuthorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    /**
     * ADD_CUSTOMER
     * UPDATE_CUSTOMER
     * REMOVE_CUSTOMER
     * VIEW_ALL_CUSTOMER
     * VIEW_CUSTOMER
     * CHECK_CUSTOMER_IF_EXIST
     *
     */
    @PreAuthorize("hasAuthority(\""+ CustomerAuthorConstants.CUSTOMER_ADD+ "\")")
    @GetMapping("add")
    public String addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @PreAuthorize("hasAuthority(\""+ CustomerAuthorConstants.CUSTOMER_UPDATE+ "\")")
    @GetMapping("update")
    public String updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @PreAuthorize("hasAuthority(\""+ CustomerAuthorConstants.CUSTOMER_REMOVE+ "\")")
    @GetMapping("remove")
    public String removeCustomer(@RequestParam String phoneNumber){
        return customerService.removeCustomer(phoneNumber);
    }

    @PreAuthorize("hasAuthority(\""+ CustomerAuthorConstants.CUSTOMER_VIEW+ "\")")
    @GetMapping("view")
    public Customer viewCustomer(@RequestParam String phoneNumber){
        return customerService.getCustomer(phoneNumber);
    }

    @PreAuthorize("hasAuthority(\""+ CustomerAuthorConstants.CUSTOMER_CHECK+ "\")")
    @GetMapping("check")
    public Boolean checkCustomerIfExist(@RequestParam String phoneNumber){
        return customerService.checkCustomer(phoneNumber);
    }

    @PreAuthorize("hasAuthority(\""+ CustomerAuthorConstants.CUSTOMER_VIEW_ALL+ "\")")
    @GetMapping("view-all")
    public Page<Customer> viewAllCustomer(@RequestParam Integer pageNo,
                                          @RequestParam Integer limit){
        return customerService.getAllCustomer(pageNo,limit);
    }


}
