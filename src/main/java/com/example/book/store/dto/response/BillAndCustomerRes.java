package com.example.book.store.dto.response;

import com.example.book.store.entities.Bill;
import com.example.book.store.repository.CustomerRepository;
import com.example.book.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BillAndCustomerRes {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;
    private String id;
    private Integer totalMoney;
    private String employeeId;
    private String employeeName;
    private String paymentType;
    private Date time;

    private String customerId;
    private String phoneNumber;
    private String customerName;

    public BillAndCustomerRes (Bill bill){
        this.id = bill.getId();
        this.totalMoney = bill.getTotalMoney();
        this.employeeId = bill.getUserId();
        this.employeeName = getEmployeeNameFromBill(id);
        this.paymentType = bill.getPaymentType();
        this.time = bill.getTime();
        this.customerId = bill.getCustomerId();
        this.customerName = getCustomerNameFromCustomer(customerId);
        this.phoneNumber = getCustomerPhoneNumberFromCustomer(customerId);

    }

    private String getCustomerPhoneNumberFromCustomer(String customerId) {
        return customerRepository.findById(customerId).get().getPhoneNumber();
    }

    private String getCustomerNameFromCustomer(String customerId) {
        return customerRepository.findById(customerId).get().getName();
    }

    private String getEmployeeNameFromBill(String id) {
        return userRepository.findById(id).get().getName();
    }

}
