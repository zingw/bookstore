package com.example.book.store.service;

import com.example.book.store.dto.ApiResponse;
import com.example.book.store.entities.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product getProductInfo(String id);

    ApiResponse<?> createProduct(Product product);

    ApiResponse<?> removeProduct(String productId);

    ApiResponse<?> updateProduct(Product product);

    Page<Product> viewProduct(Integer limit, Integer pageNo);
}
