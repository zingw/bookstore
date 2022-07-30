package com.example.book.store.entities;

import com.example.book.store.dto.request.reqcategory.UpdateCategoryReq;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name  = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Category {
    @Id
    @Column(name  = "id")
    private String id;
    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Category(UpdateCategoryReq req){
        this.id = req.getId();
        this.name = req.getName();
        this.isDeleted = req.getIsDeleted();
    }

}
