package com.example.book.store.dto.response;

import com.example.book.store.entities.Product;
import com.example.book.store.repository.CategoryRepository;
import com.example.book.store.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSearchRes {
    @Autowired
    CategoryRepository categoryRepository;
    private String id;
    private String sku;
    private String name;
    private Integer importPrice;
    private Integer sellPrice;
    private String categoryId;
    private String categoryName;
    private Boolean isDeleted;

    public ProductSearchRes(Product product){
        this.id = product.getId();
        this.sku = product.getSku();
        this.name = product.getName();
        this.importPrice = product.getSellPrice();
        this.sellPrice = product.getSellPrice();
        this.categoryId = product.getCategoryId();
        this.categoryName = generateCategoryName(product.getId());
        this.isDeleted = product.getIsDeleted();
    }

    private String generateCategoryName(String id) {
        return categoryRepository.findById(id).get().getName();
    }


}
