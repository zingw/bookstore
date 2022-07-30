package com.example.book.store.dto.request.reqcategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryReq {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotNull
    private Boolean isDeleted;
}
