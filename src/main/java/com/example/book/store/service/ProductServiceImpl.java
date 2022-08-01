package com.example.book.store.service;

import com.example.book.store.dto.common.Pagination;
import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.reqproduct.CreateProductReq;
import com.example.book.store.dto.request.reqproduct.DeleteProductReq;
import com.example.book.store.dto.request.reqproduct.SearchProductReq;
import com.example.book.store.dto.request.reqproduct.UpdateProductReq;
import com.example.book.store.dto.response.ProductRes;
import com.example.book.store.dto.response.ProductSearchRes;
import com.example.book.store.entities.Category;
import com.example.book.store.entities.Product;
import com.example.book.store.repository.CategoryRepository;
import com.example.book.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseObject<ProductRes> getProductDetail(String id) {
        Optional<Product> productOptional = productRepository.findByIdAndIsDeletedIsFalse(id);
        if(productOptional.isEmpty()) return  ResponseObject.failed("ID_NOT_FOUND", HttpStatus.NOT_FOUND);
        Product product = productOptional.get();
        return ResponseObject.success(new ProductRes(product));
    }

    @Override
    public ResponseObject<ProductRes> createProduct(CreateProductReq req) {
        //Nếu SKU đã tồn tại thì báo lỗi.
        // nếu chưa thì cho chọn Category.
        // nếu là 1 category hoàn toàn mới thì sẽ cho tạo category ở một page khác, rồi sau đó quay lại trang createProduct.
        String sku = req.getSku();
        Optional<Product> productOptional = productRepository.findBySku(sku);
        if(productOptional.isPresent()){
            return ResponseObject.failed("SKU_EXIST",HttpStatus.BAD_REQUEST);
        }
        Product product = new Product(req);
        return ResponseObject.success(new ProductRes(product));
    }

    @Override
    public ResponseObject<ProductRes> updateProduct(UpdateProductReq req) {
        String productId = req.getId();
        Optional<Product> productOptional =  productRepository.findByIdAndIsDeletedIsFalse(productId);
        if(productOptional.isEmpty()) return  ResponseObject.failed("ID_NOT_FOUND",HttpStatus.NOT_FOUND);
        Product product = new Product(req);
        return ResponseObject.success(new ProductRes(product));
    }

    @Override
    public ResponseObject<ProductRes> deleteProduct(DeleteProductReq req) {
        String productId = req.getId();
        Optional<Product> productOptional =  productRepository.findByIdAndIsDeletedIsFalse(productId);
        if(productOptional.isEmpty()) return  ResponseObject.failed("ID_NOT_FOUND",HttpStatus.NOT_FOUND);

        Product product = productOptional.get();
        product.setIsDeleted(true);
        return ResponseObject.success(new ProductRes(product));
    }


    @Override
    public ResponseObject<Page<ProductSearchRes>> getAllProduct(SearchProductReq req, Pagination page) {
        String name= req.getName();
        String categoryName = req.getCategoryName();
        String categoryId = req.getCategoryId();

        Integer pageNo = page.getPageNo();
        Integer limit = page.getLimit();

        Pageable pageable = PageRequest.of(pageNo,limit);
        Page<Product> pageProduct = productRepository.search(name,
                categoryName,
                categoryId,
                pageable);
        List<String> categoryIdList = pageProduct.stream().map(Product::getCategoryId).collect(Collectors.toList());
        return ResponseObject.success(resPageGenerator(categoryIdList,pageProduct));

    }

    @Override
    public ResponseObject<Page<ProductSearchRes>> getProductList(Pagination page) {
        Pageable pageable = PageRequest.of(page.getPageNo(),page.getLimit());
        Page<Product> pageProduct = productRepository.findAll(pageable);
        List<String> categoryIdList = pageProduct.stream().map(Product::getCategoryId).collect(Collectors.toList());
        return ResponseObject.success(resPageGenerator(categoryIdList,pageProduct));
    }

    private Page<ProductSearchRes> resPageGenerator(List<String> categoryIdList,Page<Product> pageProduct) {
        List<Category> categories = categoryRepository.findAllByIdIn(categoryIdList);
        Map<String,Category> categoryMap = categories.stream()
                .collect(Collectors.toMap(Category::getId,c ->c));
        List<ProductSearchRes> resList = new ArrayList<>();
        for(Product product : pageProduct){
            resList.add(new ProductSearchRes(product,categoryMap));
        }
        return new PageImpl<>(resList);
    }



}
