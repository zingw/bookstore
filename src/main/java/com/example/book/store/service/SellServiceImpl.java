package com.example.book.store.service;

import com.example.book.store.dto.ApiResponse;
import com.example.book.store.dto.response.CustomerResponse;
import com.example.book.store.dto.response.EmployeeResponse;
import com.example.book.store.dto.response.OrderResponse;
import com.example.book.store.dto.response.ProductResponse;
import com.example.book.store.entities.Bill;
import com.example.book.store.entities.Customer;
import com.example.book.store.entities.OrderDetail;
import com.example.book.store.repository.*;
import com.example.book.store.utils.SecurityContextHolderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellServiceImpl implements SellService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ApiResponse<OrderResponse> sell(Map<String, Integer> purchaseMap,
                                           String paymentType,
                                           String customerPhone,
                                           Integer discountPercent, // value từ 1--> 100
                                           Integer discountOther,   // Số tiền, value ko hạn chế
                                           String note) {

        if (purchaseMap.isEmpty()) throw new RuntimeException("NOTHING_TO_SELL");
        // generate OrderResponse
        String billId = UUID.randomUUID().toString();
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<ProductResponse> productResponseList = new ArrayList<>();
        int totalPayBeforeDiscount = 0;
        int totalPayAfterDiscount;

        for (String productId : purchaseMap.keySet()) {

            orderDetails.add(buildOrderDetail(productId, billId, purchaseMap));
            ProductResponse prodRes = buildProductResponse(productId, purchaseMap);
            productResponseList.add(prodRes);
            totalPayBeforeDiscount += prodRes.getMoney();
        }
        totalPayAfterDiscount = (int) (totalPayBeforeDiscount * (1 - discountPercent/ 100.0))  - discountOther;
        EmployeeResponse employeeResponse = employeeResponseGenerator();
        CustomerResponse customerResponse = customerResponseGenerator(customerPhone);
        Date orderTime = new Date();

        // save info to repository
        Bill bill = new Bill(
                            billId,
                            totalPayAfterDiscount,
                            employeeResponse.getId(),
                            paymentType,
                            orderTime,
                            customerResponse.getId(),
                            discountPercent,
                            discountOther,
                            note);
        billRepository.save(bill);
        orderDetailRepository.saveAll(orderDetails);

        return new ApiResponse<>("Thanh toán thành công",true,new OrderResponse(
                                                                                                productResponseList,
                                                                                                employeeResponse,
                                                                                                customerResponse,
                                                                                                orderTime, paymentType,
                                                                                                totalPayAfterDiscount));

    }

    private ProductResponse buildProductResponse(String productId, Map<String, Integer> purchaseMap) {
        if (productRepository.findById(productId).isEmpty()) throw new RuntimeException("PRODUCT_NOT_FOUND " + productId);

        String productName = productRepository.findById(productId).get().getName();
        Integer quantity = purchaseMap.get(productId);
        Integer price = productRepository.findById(productId).get().getSellPrice();
        Integer money = price * quantity;

        return new ProductResponse(productId, productName, quantity, price, money);
    }

    private OrderDetail buildOrderDetail(String productId, String billId, Map<String, Integer> purchaseMap) {
        return new OrderDetail(
                UUID.randomUUID().toString(),
                billId,
                productId,
                productRepository.findById(productId).get().getSellPrice(),
                purchaseMap.get(productId));
    }

    private CustomerResponse customerResponseGenerator(String customerPhone) {
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(customerPhone);
        // phòng trường hợp nhân viên nhập lại sai sdt của khách
        if (customerOptional.isPresent()) {
            return new CustomerResponse(customerOptional.get());
        } else return new CustomerResponse();

    }

    private EmployeeResponse employeeResponseGenerator() {
        // Ko cần check null vì khi login thành công mặc định sẽ có username chuẩn.
        String usernameOfEmployee = SecurityContextHolderUtils.getUserName();
        return new EmployeeResponse(userRepository.findByUsername(usernameOfEmployee).get());
    }

}
