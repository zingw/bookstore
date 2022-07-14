package com.example.book.store.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CommonListRes {
    private List<?> list;
    private Integer pageNo;
    private Integer limit;
    private Integer totalPage;
}
