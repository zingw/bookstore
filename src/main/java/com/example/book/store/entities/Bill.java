package com.example.book.store.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Entity
@Table(name  = "bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Bill {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "total")
    private Integer total;

    @Column(name = "username")
    private String username;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name ="time")
    private Date time;
}
