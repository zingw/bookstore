package com.example.book.store.dto.request.reqproduct;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdateProductReq {
    private String id;
    private String name;
    private Integer importPrice;
    private Integer sellPrice;
    private String categoryId;
    private Boolean isDeleted;
}
