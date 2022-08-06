package com.example.book.store.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private List<ProductRes> productResList;
    private EmployeeResponse employeeResponse;
    private CustomerResponse customerResponse;
    private Date orderTime;
    private String paymentType;
}
