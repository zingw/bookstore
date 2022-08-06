package com.example.book.store.dto.response;

import com.example.book.store.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRes {

    private String id;
    private String name;

    private String sku;
    private Integer importPrice;
    private Integer sellPrice;
    private String categoryId;
    private Boolean isDeleted;

    public ProductRes(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.sku = product.getSku();
        this.importPrice = product.getImportPrice();
        this.sellPrice = product.getSellPrice();
        this.categoryId = product.getCategoryId();
        this.isDeleted = product.getIsDeleted();
    }
}
