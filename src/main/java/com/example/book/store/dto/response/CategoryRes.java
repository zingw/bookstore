package com.example.book.store.dto.response;

import com.example.book.store.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryRes {

    private String id;
    private String name;

    public CategoryRes(Category category){
        this.id = category.getId();
        this.name = category.getName();
    }

}
