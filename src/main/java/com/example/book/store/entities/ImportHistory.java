package com.example.book.store.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name  = "import_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ImportHistory {
    @Id
    @Column(name ="id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "date")
    private Date importTime;
}
