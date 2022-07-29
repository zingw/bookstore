package com.example.book.store.service;

import com.example.book.store.dto.request.CreateCategoryReq;
import com.example.book.store.dto.request.UpdateCategoryReq;
import com.example.book.store.dto.response.CategoryRes;
import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.entities.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    ResponseObject<CategoryRes> createCategory(CreateCategoryReq req);

    ResponseObject<List<Category>> getCategoryList(Pageable pageable);

    ResponseObject<CategoryRes> updateCategory(UpdateCategoryReq req);

    ResponseObject<CategoryRes> deleteCategory(String id);

    ResponseObject<CategoryRes> getCategoryDetail(String id);
}
