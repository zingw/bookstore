package com.example.book.store.dto.request.reqcategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter

public class CreateCategoryReq {
    @NotBlank
    private String name;

}
