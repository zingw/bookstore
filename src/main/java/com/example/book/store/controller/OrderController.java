package com.example.book.store.controller;

import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.orderreq.OrderRequest;
import com.example.book.store.dto.response.OrderResponse;
import com.example.book.store.dto.response.ProductRes;
import com.example.book.store.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<ResponseObject<OrderResponse>> order(@RequestBody OrderRequest req){
        ResponseObject<OrderResponse> res =
                orderService.order(req);
        return new ResponseEntity<>(res,res.getStatus());
    }
}
