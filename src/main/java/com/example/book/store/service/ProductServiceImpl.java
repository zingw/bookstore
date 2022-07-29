package com.example.book.store.service;

import com.example.book.store.dto.common.Pagination;
import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.request.CreateProductReq;
import com.example.book.store.dto.request.DeleteProductReq;
import com.example.book.store.dto.request.SearchProductReq;
import com.example.book.store.dto.request.UpdateProductReq;
import com.example.book.store.dto.response.CategoryRes;
import com.example.book.store.dto.response.ProductRes;
import com.example.book.store.dto.response.ProductSearchRes;
import com.example.book.store.entities.Category;
import com.example.book.store.entities.Product;
import com.example.book.store.repository.CategoryRepository;
import com.example.book.store.repository.ProductRepository;
import com.example.book.store.utils.RandomIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
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
        Product product = new Product(req);
        return ResponseObject.success(new ProductRes(product));
    }

    @Override
    public ResponseObject<Page<ProductRes>> getAllProduct(Pagination page) {
        Pageable pageable = PageRequest.of(page.getPageNo()-1, page.getLimit());
        Page<Product>  productPage =  productRepository.findByIsDeletedFalse(pageable);
        List<ProductRes> resList = productPage.stream().map(ProductRes::new).collect(Collectors.toList());
        Page<ProductRes> resPage = new PageImpl<>(resList);
        return ResponseObject.success(resPage);
    }

    @Override
    public ResponseObject<Page<ProductSearchRes>> searchProduct(SearchProductReq req,Pagination page) {
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
        List<ProductSearchRes>  resList = pageProduct.stream().map(ProductSearchRes::new).collect(Collectors.toList());
        Page<ProductSearchRes> resPage = new PageImpl<>(resList);
        return ResponseObject.success(resPage);

    }

    @Override
    public ResponseObject<Page<ProductSearchRes>> getProductJoinCategoryList(Pagination page) {
        Pageable pageable = PageRequest.of(page.getPageNo(),page.getLimit());
        Page<Product> pageProduct = productRepository.findAll(pageable);
        List<ProductSearchRes>  resList = pageProduct.stream().map(ProductSearchRes::new).collect(Collectors.toList());
        Page<ProductSearchRes> resPage = new PageImpl<>(resList);
        return ResponseObject.success(resPage);
    }



}
