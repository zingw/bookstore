package com.example.book.store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name  = "line_order")
@AllArgsConstructor
@NoArgsConstructor
public class LineOrder {
    //Id
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "bill_id")
    private String billId;
    @Column(name ="product_id")
    private String productId;
    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private Integer quantity;


}
