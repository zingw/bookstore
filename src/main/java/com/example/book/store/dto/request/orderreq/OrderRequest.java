package com.example.book.store.dto.request.orderreq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private Map<String,Integer> orders;
    private String customerId;
    private String paymentType;
}
