package com.example.book.store.service;
import com.example.book.store.dto.ApiResponse;
import com.example.book.store.entities.Bill;
import com.example.book.store.entities.OrderDetail;
import com.example.book.store.repository.BillRepository;
import com.example.book.store.repository.OrderRepository;
import com.example.book.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SellServiceImpl implements SellService{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    BillRepository billRepository;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public ApiResponse<?> sell(Map<String, Integer> purchaseMap, String paymentType) {
        int total = 0;
        // check map is correct or not
        for(String s : purchaseMap.keySet()){
            if(productRepository.findById(s).isEmpty()){
                return  new ApiResponse<>("Id" + s + "không tồn tại trên hệ thống, kiểm tra lại",0);
            }
            total += productRepository.findById(s).get().getSellPrice()
                    *purchaseMap.get(s);
        }

        //tach SecurityContext... ra 1 class rieng
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(username);

        Bill bill = new Bill(UUID.randomUUID().toString(),total,username,paymentType,new Date());
        billRepository.save(bill);
        List<OrderDetail> orderDetailList = new ArrayList<>();

        // getorderDetailListBy...
        for(String s: purchaseMap.keySet()){
            orderDetailList.add( new OrderDetail(   UUID.randomUUID().toString(),
                                                    bill.getId(),
                                                    s,
                                                    productRepository.findById(s).get().getSellPrice(),
                                                    purchaseMap.get(s)));
        }
        orderRepository.saveAll(orderDetailList);
        return new ApiResponse<>("Thanh toán thành công",total);
        //
    }
}
