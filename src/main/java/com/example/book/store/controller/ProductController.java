package com.example.book.store.controller;

import com.example.book.store.dto.ApiResponse;
import com.example.book.store.entities.Product;
import com.example.book.store.service.ProductService;
import com.example.book.store.utils.PermissionAuthorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize("hasAuthority(\"" + PermissionAuthorConstants.PRODUCT_VIEW + "\")")
    @GetMapping("/info")
    public Product getProductInfo(@RequestParam String id){
        return productService.getProductInfo(id);
    }

    @PreAuthorize("hasAuthority(\"" + PermissionAuthorConstants.PRODUCT_VIEW_ALL + "\")")
    @GetMapping("/view")
    public Page<Product> viewProduct(@RequestParam Integer limit,
                                     @RequestParam Integer pageNo){
        return productService.viewProduct(limit,pageNo);
    }

    @PreAuthorize("hasAuthority(\"" + PermissionAuthorConstants.PRODUCT_CREATE + "\")")
    @GetMapping(value = "/create")
    public ApiResponse<?> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PreAuthorize("hasAuthority(\"" + PermissionAuthorConstants.PRODUCT_REMOVE + "\")")
    @GetMapping(value = "/remove")
    public ApiResponse<?> removeProduct(@RequestParam String productId){
        return productService.removeProduct(productId);
    }

    @PreAuthorize("hasAuthority(\"" + PermissionAuthorConstants.PRODUCT_UPDATE + "\")")
    @GetMapping("/update")
    public ApiResponse<?> updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }


}
