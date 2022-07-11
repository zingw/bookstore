package com.example.book.store.service;

import com.example.book.store.dto.ApiResponse;
import com.example.book.store.entities.Product;
import com.example.book.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product getProductInfo(String id) {
        Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.orElse(null);
    }

    @Override
    public ApiResponse<?> createProduct(Product product) {
        // check xem product da ton tai hay chua ?
        String productId = product.getId();
        Optional<Product>  productOptional = productRepository.findById(productId);
        if(productOptional.isPresent()){
            return new ApiResponse<>("ID sản phẩm đã tồn tại",productOptional.get());
        }
        productRepository.save(product);
        return new ApiResponse<>("Thêm sản phầm thành công",product);
    }

    @Override
    public ApiResponse<?> removeProduct(String productId) {
        Optional<Product> productOptional  = productRepository.findById(productId);
        if(productOptional.isPresent()){
            productRepository.delete(productOptional.get());
            return new ApiResponse<>("Xóa sản phẩm thành công",productOptional.get());
        }

        return new ApiResponse<>("Xóa không thành công vì ID này không đúng",null);
    }

    @Override
    public ApiResponse<?> updateProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if(productOptional.isPresent()){
            productRepository.save(product);
            return  new ApiResponse<>("Cập nhật sản phẩm thành công",product);
        }
        return new ApiResponse<>("Cập nhật sản phẩm không thành công vì Id không đúng",null);
    }

    @Override
    public Page<Product> viewProduct(Integer limit, Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo,limit);
        return productRepository.findAll(pageable);
    }
}
