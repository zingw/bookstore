package com.example.book.store.dto.response;

import com.example.book.store.entities.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillResponse {

    private String id;
    private Integer totalMoney;
    private String userId;
    private String paymentType;
    private Date time;
    private String customerId;
    private Boolean isDeleted;

    public BillResponse(Bill bill){
        this.id = bill.getId();
        this.totalMoney = bill.getTotalMoney();
        this.userId = bill.getUserId();
        this.paymentType = bill.getPaymentType();
        this.time = bill.getTime();
        this.customerId = bill.getCustomerId();

    }
}
