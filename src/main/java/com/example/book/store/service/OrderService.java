package com.example.book.store.service;

import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.orderreq.OrderRequest;
import com.example.book.store.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface OrderService {
    ResponseObject<OrderResponse> order(OrderRequest req);
}
