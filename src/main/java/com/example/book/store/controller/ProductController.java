package com.example.book.store.controller;

import com.example.book.store.dto.common.Pagination;
import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.*;
import com.example.book.store.dto.response.CategoryRes;
import com.example.book.store.dto.response.ProductRes;
import com.example.book.store.dto.response.ProductSearchRes;
import com.example.book.store.entities.Product;
import com.example.book.store.service.ProductService;
import com.example.book.store.constants.PermissionAuthorConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize("hasAuthority(\"" + PermissionAuthorConstants.PRODUCT_VIEW + "\")")
    @GetMapping("/get-product-detail")
    public ResponseEntity<ResponseObject<ProductRes>> getProductDetail(@RequestParam String id){

        ResponseObject<ProductRes> res =  productService.getProductDetail(id);
        return new ResponseEntity<>(res,res.getStatus());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject<ProductRes>> createProduct(@Valid @RequestBody
                                                                    CreateProductReq req){
        ResponseObject<ProductRes> productObjRes =
                productService.createProduct(req);
        return new ResponseEntity<>(productObjRes,productObjRes.getStatus());
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject<ProductRes>> updateProduct(@Valid
                                                                    @RequestBody UpdateProductReq req){
        ResponseObject<ProductRes> productObjRes =
                productService.updateProduct(req);
        return new ResponseEntity<>(productObjRes,productObjRes.getStatus());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseObject<ProductRes>> deleteProduct(@Valid
                                                                    @RequestBody DeleteProductReq req){
        ResponseObject<ProductRes> productObjRes =
                productService.deleteProduct(req);
        return new ResponseEntity<>(productObjRes,productObjRes.getStatus());
    }

    @GetMapping("/get-all-product")
    public ResponseEntity<ResponseObject<Page<ProductRes>>> getAllProduct(@Valid
                                                                          @RequestBody Pagination page){
        ResponseObject<Page<ProductRes>> res =
                productService.getAllProduct(page);
        return new ResponseEntity<>(res,res.getStatus());

    }
    @GetMapping("/product/search")
    public ResponseEntity<ResponseObject<Page<ProductSearchRes>>> getProductJoinCategoryList(@Valid @RequestBody Pagination page){
        ResponseObject<Page<ProductSearchRes>> res =
                productService.getProductJoinCategoryList(page);
        return new ResponseEntity<>(res,res.getStatus());

    }
    @GetMapping("/search-product/{pageNo}/{limit}")
    public ResponseEntity<ResponseObject<Page<ProductSearchRes>>> searchProduct(@RequestBody SearchProductReq req,
                                                                          @Valid @Min(1)
                                                                          @PathVariable("pageNo") Integer pageNo,
                                                                          @Min(1)
                                                                          @PathVariable("limit") Integer limit){
        Pagination pagination = new Pagination(pageNo-1,limit);
        ResponseObject<Page<ProductSearchRes>> res =
                productService.searchProduct(req,pagination);
        return new ResponseEntity<>(res,res.getStatus());
    }


}
