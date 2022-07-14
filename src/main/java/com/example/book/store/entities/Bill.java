package com.example.book.store.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name  = "bill")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "total_money")
    private Integer totalMoney;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name ="time")
    private Date time;

    @Column(name ="customer_id")
    private String customerId;

    @Column(name = "discount_percent")
    private Integer discountPercent;

    @Column(name = "discount_other")
    private Integer discountOther;

    @Column(name = "note")
    private String note;
    // theo tong so tien : >10tr ->disc 5%
    //                           ->100tr-> disc 10%

    // thep V.I.P -> giam them 5% nua
    // giam them 5% neu thanh toan = VNPAY / MOMO
}
