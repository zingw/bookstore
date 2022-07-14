package com.example.book.store.service;

import com.example.book.store.dto.response.BillAndCustomerRes;
import com.example.book.store.dto.response.CommonListRes;
import com.example.book.store.dto.response.CustomBillRes;
import com.example.book.store.entities.Bill;
import com.example.book.store.entities.Customer;
import com.example.book.store.entities.User;
import com.example.book.store.repository.BillRepository;
import com.example.book.store.repository.CustomerRepository;
import com.example.book.store.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class BillServiceImpl implements BillService{

    @Autowired
    BillRepository billRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CommonListRes getAllBill(Integer pageNo, Integer limit) {
        Pageable pageable = PageRequest.of(pageNo,limit);
        Page<Bill> pageBill = billRepository.findAll(pageable);
        List<CustomBillRes> customBillResList = pageBill
                                                        .getContent()
                                                        .stream()
                                                        .map(CustomBillRes::new)
                                                        .collect(Collectors.toList());
        Integer totalPage = pageBill.getTotalPages();

        return new CommonListRes(customBillResList,pageNo,limit,totalPage);
    }

    @Override
    public CustomBillRes getBillDetail(String billId) {
        Optional<Bill> billOptional = billRepository.findById(billId);
        if(billOptional.isEmpty()){
            throw new RuntimeException("BILL_NOT_FOUND : "+billId);
        }
        return new CustomBillRes(billOptional.get());
    }

    @Override
    public CommonListRes findBill(String id,
                                             String employeeName,
                                             String paymentType,
                                             Date   startTime,
                                             Date   endTime,
                                             String customerName,
                                             String customerPhone,
                                             Integer pageNo,
                                             Integer limit) {
        log.debug("=========> START FIND BILL VER 1");
        Long startRunTime = new Date().getTime();

        Pageable pageable = PageRequest.of(pageNo,limit);
        Optional<List<User>> employeeListOptional  = userRepository.findByName(employeeName);
        if (employeeListOptional.isEmpty()) return new CommonListRes(null,0,10,1);
        List<User> employeeList = employeeListOptional.get();
        List<String> employeeIdList = employeeList.stream().map(User::getId).collect(Collectors.toList());          // lấy ra danh sách mã nhân viên match với name

        Optional<List<Customer>> customerListOptional = customerRepository.findByName(customerName);
        if(customerListOptional.isEmpty()) return new CommonListRes(null,0,10,1);
        List<Customer> customerList = customerListOptional.get();
        List<String> customerIdList = customerList.stream().map(Customer::getId).collect(Collectors.toList());          // lấy ra danh sách mã KH match với name

        Page<Bill> billPage =  billRepository.findBill( id, employeeIdList,
                                                        paymentType,
                                                        startTime,
                                                        endTime,
                                                        customerIdList,
                                                        customerPhone,
                                                        pageable);
        List<Bill> bills = billPage.getContent();
        List<BillAndCustomerRes> billAndCustomerResList = bills.stream().map(BillAndCustomerRes::new).collect(Collectors.toList()); // convert sang 1 list chứa cả thông tin của bill và customer
        int totalPage = billPage.getTotalPages();

        Long endRunTime = new Date().getTime();
        log.debug("Duration: " + (endRunTime - startRunTime)/ 1000 + "s");
        return new CommonListRes(billAndCustomerResList,pageNo,limit,totalPage);
    }

    @Override
    public CommonListRes findBillVer2(String id, String employeeName,
                                  String paymentType,
                                  Date   startTime,
                                  Date   endTime,
                                  String customerName,
                                  String customerPhone,
                                  Integer pageNo,
                                  Integer limit) {

        log.debug("=========> START FIND BILL VER 2");
        Long startRunTime = new Date().getTime();
        Pageable pageable = PageRequest.of(pageNo, limit);
        Page<Bill> billPage =  billRepository.findBillVer2( id, employeeName,
                paymentType,
                startTime,
                endTime,
                customerName,
                customerPhone,
                pageable);
        List<Bill> bills = billPage.getContent();
        List<BillAndCustomerRes> billAndCustomerResList = bills.stream().map(BillAndCustomerRes::new).collect(Collectors.toList()); // convert sang 1 list chứa cả thông tin của bill và customer
        int totalPage = billPage.getTotalPages();

        Long endRunTime = new Date().getTime();
        log.debug("Duration: " + (endRunTime - startRunTime)/ 1000 + "s");
        return new CommonListRes(billAndCustomerResList,pageNo,limit,totalPage);
    }

}
