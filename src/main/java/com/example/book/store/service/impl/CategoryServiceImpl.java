package com.example.book.store.service.impl;

import com.example.book.store.dto.request.reqcategory.CreateCategoryReq;
import com.example.book.store.dto.request.reqcategory.UpdateCategoryReq;
import com.example.book.store.dto.response.CategoryRes;
import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.entities.Category;
import com.example.book.store.repository.CategoryRepository;
import com.example.book.store.service.CategoryService;
import com.example.book.store.utils.RandomIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseObject<CategoryRes> createCategory(CreateCategoryReq req) {
        Category category = new Category();

        category.setId(RandomIdUtils.getRanDomId());
        category.setName(req.getName());
        category.setIsDeleted(false);

        CategoryRes categoryRes = new CategoryRes(category);
        return ResponseObject.success(categoryRes);
    }

    @Override
    public ResponseObject<List<Category>> getCategoryList(Pageable pageable) {
        List<Category> categories =  categoryRepository.findAllByIsDeletedIsFalse(pageable);
        return ResponseObject.success(categories);
    }

    @Override
    public ResponseObject<CategoryRes> getCategoryDetail(String id) {
        Optional<Category> categoryOptional = categoryRepository.findByIdAndIsDeletedIsFalse(id);
        if(categoryOptional.isEmpty()) return  ResponseObject.failed("ID_NOT_FOUND",HttpStatus.NOT_FOUND);
        Category category = categoryOptional.get();
        return ResponseObject.success(new CategoryRes(category));
    }

    @Override
    public ResponseObject<CategoryRes> updateCategory(UpdateCategoryReq req) {
        String categoryId = req.getId();

        Optional<Category> categoryOptional = categoryRepository.findByIdAndIsDeletedIsFalse(categoryId);
        if(categoryOptional.isEmpty()) return  ResponseObject.failed("ID_NOT_FOUND",HttpStatus.NOT_FOUND);

        Category category = categoryOptional.get();
        category.setName(req.getName());
        category.setIsDeleted(req.getIsDeleted());


        categoryRepository.save(category);
        return ResponseObject.success(new CategoryRes(category));
    }

    @Override
    public ResponseObject<CategoryRes> deleteCategory(String id) {
        Optional<Category> categoryOptional = categoryRepository.findByIdAndIsDeletedIsFalse(id);
        if(categoryOptional.isEmpty()) return  ResponseObject.failed("ID_NOT_FOUND",HttpStatus.NOT_FOUND);

        Category category = categoryOptional.get();
        category.setIsDeleted(true);
        return ResponseObject.success(new CategoryRes(category));
    }


}
