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
                                           Integer discountPercent,
                                           Integer discountOther,
                                           String note) {

        // generate OrderResponse
        List<ProductResponse> productResponseList = generateProductResponseList(purchaseMap);
        if (productResponseList == null) {
            return new ApiResponse<>("Danh Sách Lỗi", false, null);
        }
        EmployeeResponse employeeResponse = employeeResponseGenerator();
        CustomerResponse customerResponse = customerResponseGenerator(customerPhone);
        Date orderTime = new Date();
        String payment = paymentType;
        Integer totalPayAfterDiscount = getTotalPayBeforeDiscount(productResponseList) * (100 - discountOther - discountPercent);

        // save info to repository
        Bill bill = new Bill(
                            UUID.randomUUID().toString(),
                            getTotalPayBeforeDiscount(productResponseList),
                            employeeResponse.getId(),
                            paymentType,
                            orderTime,
                            discountPercent,
                            discountOther,
                            note);
        billRepository.save(bill);
        for (String productId : purchaseMap.keySet()) {
            OrderDetail orderDetail = new OrderDetail(
                                                    UUID.randomUUID().toString(),
                                                    bill.getId(),
                                                    productId,
                                                    productRepository.findById(productId).get().getSellPrice(),
                                                    purchaseMap.get(productId));
            orderDetailRepository.save(orderDetail);
        }

        return new ApiResponse<>("Thanh toán thành công",true,new OrderResponse(
                                                                                                productResponseList,
                                                                                                employeeResponse,
                                                                                                customerResponse,
                                                                                                orderTime,payment,
                                                                                                totalPayAfterDiscount));

    }

    private Integer getTotalPayBeforeDiscount(List<ProductResponse> productResponseList) {
        Integer total = 0;
        for(ProductResponse productResponse :productResponseList){
            total += productResponse.getMoney();
        }
        return total;
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

    private List<ProductResponse> generateProductResponseList(Map<String, Integer> purchaseMap) {
        List<ProductResponse> productResponseList = new ArrayList<>();

        for (String productId : purchaseMap.keySet()) {
            if (productRepository.findById(productId).isEmpty()) {
                return null;
            }

            String productName = productRepository.findById(productId).get().getName();
            Integer quantity = purchaseMap.get(productId);
            Integer price = productRepository.findById(productId).get().getSellPrice();
            Integer money = price * quantity;
            productResponseList.add(new ProductResponse(productId, productName, quantity, price, money));
        }
        return productResponseList;
    }
}
