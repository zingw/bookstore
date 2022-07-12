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
@Table(name  = "orderdetail")
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
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
