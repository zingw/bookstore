package com.example.book.store.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name  = "bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Stock {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "quantity")
    private Integer quantity;
}
