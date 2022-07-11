package com.example.book.store.controller;

import com.example.book.store.dto.ApiResponse;
import com.example.book.store.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sell")
public class SellController {
    @Autowired
    SellService sellService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/sell")
    public ApiResponse<?> sell(@RequestBody Map<String,Integer> purchaseMap,
                               @RequestParam String paymentType){
        return sellService.sell(purchaseMap,paymentType);
    }


}
