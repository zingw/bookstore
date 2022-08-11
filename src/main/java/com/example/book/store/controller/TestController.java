package com.example.book.store.controller;

import com.example.book.store.dto.response.ProductInfo;
import com.example.book.store.repository.ProductRepository;
import com.example.book.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test-api")
public class TestController {

    @Autowired
    ProductService productService;

    @GetMapping("/get-product-info")
    public List<ProductInfo> getProductInfoList(){
        return productService.getProductInfoList();
    }
}
