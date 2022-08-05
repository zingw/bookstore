package com.example.book.store.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name  = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Inventory {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "quantity")
    private Integer quantity;


}
