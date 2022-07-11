package com.example.book.store.service;

import com.example.book.store.dto.ApiResponse;

import java.util.Map;

public interface SellService {
    ApiResponse<?> sell(Map<String, Integer> purchaseMap, String paymentType);
}
