package com.example.book.store.service;

import com.example.book.store.dto.common.Pagination;
import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.reqproduct.CreateProductReq;
import com.example.book.store.dto.request.reqproduct.DeleteProductReq;
import com.example.book.store.dto.request.reqproduct.SearchProductReq;
import com.example.book.store.dto.request.reqproduct.UpdateProductReq;
import com.example.book.store.dto.response.ProductInfo;
import com.example.book.store.dto.response.ProductRes;
import com.example.book.store.dto.response.ProductSearchRes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ResponseObject<ProductRes> createProduct(CreateProductReq req);

    ResponseObject<ProductRes> getProductDetail(String id);

    ResponseObject<ProductRes> updateProduct(UpdateProductReq req);

    ResponseObject<ProductRes> deleteProduct(DeleteProductReq req);

    ResponseObject<Page<ProductSearchRes>> getAllProduct(SearchProductReq req, Pagination pagination);

    ResponseObject<Page<ProductSearchRes>> getProductList(Pagination page);

    List<ProductInfo> getProductInfoList();
}
