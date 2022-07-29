package com.example.book.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CreateProductReq {

    private String name;
    private String sku;
    private Integer importPrice;
    private Integer sellPrice;
    private String categoryId;
    private Boolean isDeleted;
}
