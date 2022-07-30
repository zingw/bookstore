package com.example.book.store.dto.response;

import com.example.book.store.entities.Category;
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
    private String id;
    private String sku;
    private String name;
    private Integer importPrice;
    private Integer sellPrice;
    private CategoryRes categoryRes;
    private Boolean isDeleted;

    public ProductSearchRes(Product product, Category category){
        this.id = product.getId();
        this.sku = product.getSku();
        this.name = product.getName();
        this.importPrice = product.getSellPrice();
        this.sellPrice = product.getSellPrice();
        this.categoryRes = new CategoryRes(category);
        this.isDeleted = product.getIsDeleted();
    }




}
