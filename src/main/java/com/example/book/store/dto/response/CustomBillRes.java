package com.example.book.store.dto.response;


import com.example.book.store.entities.Bill;
import com.example.book.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomBillRes {
    @Autowired
    UserRepository userRepository;

    private String id;
    private Integer actualPayAfterDiscount;
    private String employeeId;
    private String employeeName;
    private Date time;

    public CustomBillRes( Bill bill){
        this.id = bill.getId();
        this.actualPayAfterDiscount = bill.getTotalMoney() - bill.getDiscountOther()
                - bill.getTotalMoney()* bill.getDiscountPercent();
        this.employeeId = bill.getUserId();
        this.employeeName = userRepository.findById(employeeId).get().getName();
        this.time = bill.getTime();
    }

}
