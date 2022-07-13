package com.example.book.store.dto.response;

import com.example.book.store.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private String id;
    private String name;

    public EmployeeResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
    }
}
