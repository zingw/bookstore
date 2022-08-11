package com.example.book.store.repository;

import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.dto.response.ProductInfo;
import com.example.book.store.dto.response.ProductRes;
import com.example.book.store.dto.response.ProductSearchRes;
import com.example.book.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product,String> {
    Optional<Product> findByIdAndIsDeletedIsFalse(String id);

    Optional<Product> findBySku(String sku);


    @Query(value = "select  p from Product p join Category  c on p.categoryId = c.id where " +
            " (:name is null or p.name like :name) and " +
            "(:categoryName is null or c.name like :categoryName ) and " +
            " (:categoryId is null or p.categoryId = :categoryId )")
    Page<Product> search(String name, String categoryName, String categoryId, Pageable pageable);

    List<Product> findByIdIn(List<String> productIds);

    @Query(value = "select p.id as id, p.name as name, c.name as categoryName from Product p left join " +
            " Category  c on p.categoryId = c.id")
    List<ProductInfo> getProductList();
}
