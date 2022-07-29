package com.example.book.store.service;

import com.example.book.store.dto.common.Pagination;
import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.CreateProductReq;
import com.example.book.store.dto.request.DeleteProductReq;
import com.example.book.store.dto.request.SearchProductReq;
import com.example.book.store.dto.request.UpdateProductReq;
import com.example.book.store.dto.response.ProductRes;
import com.example.book.store.dto.response.ProductSearchRes;
import com.example.book.store.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ResponseObject<ProductRes> createProduct(CreateProductReq req);

    ResponseObject<ProductRes> getProductDetail(String id);

    ResponseObject<ProductRes> updateProduct(UpdateProductReq req);

    ResponseObject<ProductRes> deleteProduct(DeleteProductReq req);

    ResponseObject<Page<ProductRes>> getAllProduct(Pagination page);

    ResponseObject<Page<ProductSearchRes>> searchProduct(SearchProductReq req,Pagination pagination);

    ResponseObject<Page<ProductSearchRes>> getProductJoinCategoryList(Pagination page);
}
