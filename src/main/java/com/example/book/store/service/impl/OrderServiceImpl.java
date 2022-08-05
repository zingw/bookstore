package com.example.book.store.service.impl;

import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.orderreq.OrderRequest;
import com.example.book.store.dto.response.CustomerResponse;
import com.example.book.store.dto.response.EmployeeResponse;
import com.example.book.store.dto.response.OrderResponse;
import com.example.book.store.dto.response.ProductRes;
import com.example.book.store.entities.Inventory;
import com.example.book.store.entities.LineOrder;
import com.example.book.store.entities.Product;
import com.example.book.store.entities.User;
import com.example.book.store.repository.*;
import com.example.book.store.service.OrderService;
import com.example.book.store.utils.SecurityContextHolderUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    BillRepository billRepository;

    @Autowired
    LineOrderRepository lineOrderRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseObject<OrderResponse> order(OrderRequest req) {
        // check input có valid hay không ?
        Map<String, Integer> orders = req.getOrders();
        List<String> productIds = new ArrayList<>(orders.keySet());
        List<String> subtractIds = checkPurchaseList(productIds);
        if (subtractIds.size() > 0) {
            return ResponseObject.failed("INVALID ID(S) " + subtractIds, HttpStatus.NOT_FOUND);
        }
        //inventory repository
        List<Inventory> currInventories = inventoryRepository.findByProductIdIn(orders.keySet());
        for(Inventory inv : currInventories){
            inv.setQuantity(inv.getQuantity()-orders.get(inv.getProductId()));
        }
        inventoryRepository.saveAll(currInventories);
        
        //return ...
        List<Product> products = productRepository.findByIdIn(productIds);
        List<ProductRes> productResList = products.stream().map(ProductRes::new).collect(Collectors.toList());
        String username = SecurityContextHolderUtils.getUserName();
        EmployeeResponse employeeResponse = new EmployeeResponse(userRepository.findByUsername(username).get());
        CustomerResponse customerResponse = new CustomerResponse(customerRepository.findById(req.getCustomerId()).get());
        Date orderTime = new Date();
        String paymentType = req.getPaymentType();
        return ResponseObject.success(new OrderResponse(productResList, employeeResponse, customerResponse, orderTime, paymentType));
    }


    public List<String> checkPurchaseList(List<String> productIds) {
        List<Product> products = productRepository.findByIdIn(productIds);
        List<String> productIdsRepo = products.stream().map(Product::getId).collect(Collectors.toList());
        return ListUtils.subtract(productIds, productIdsRepo);
    }
}
