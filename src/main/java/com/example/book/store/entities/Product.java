package com.example.book.store.entities;

import lombok.*;

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
    @Column(name = "import_price")
    private Integer importPrice;
    @Column(name ="sell_price")
    private Integer sellPrice;
    @Column(name = "category_id")
    private String categoryId;
}
