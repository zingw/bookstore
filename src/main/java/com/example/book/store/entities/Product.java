package com.example.book.store.entities;

import com.example.book.store.dto.request.CreateProductReq;
import com.example.book.store.dto.request.DeleteProductReq;
import com.example.book.store.dto.request.UpdateProductReq;
import com.example.book.store.repository.CategoryRepository;
import com.example.book.store.utils.RandomIdUtils;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name  = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Product {


    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "sku")
    private String sku;
    @Column(name = "import_price")
    private Integer importPrice;
    @Column(name ="sell_price")
    private Integer sellPrice;
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Product(CreateProductReq req){
        this.id = RandomIdUtils.getRanDomId();
        this.name = req.getName();
        this.sku = req.getSku();
        this.importPrice = req.getImportPrice();
        this.sellPrice = req.getSellPrice();
        this.isDeleted = req.getIsDeleted();
        this.categoryId = req.getCategoryId();
    }

    public Product(UpdateProductReq req){
        this.name = req.getName();
        this.importPrice = req.getImportPrice();
        this.sellPrice = req.getSellPrice();
        this.categoryId = req.getCategoryId();
        this.isDeleted = false;
    }

    public Product(DeleteProductReq req){
        this.id = req.getId();
        this.isDeleted = true;
    }

}
