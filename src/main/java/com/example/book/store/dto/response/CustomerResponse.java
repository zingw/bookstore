package com.example.book.store.dto.response;

import com.example.book.store.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResponse {
    private String id;
    private String name;

    public CustomerResponse(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
    }
}
