package com.example.book.store.controller;

import com.example.book.store.dto.request.reqcategory.CreateCategoryReq;
import com.example.book.store.dto.request.reqcategory.UpdateCategoryReq;
import com.example.book.store.dto.response.CategoryRes;
import com.example.book.store.dto.common.ResponseObject;
import com.example.book.store.entities.Category;
import com.example.book.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseObject<CategoryRes>> createCategory(@Valid @RequestBody
                                                                      CreateCategoryReq req){
         ResponseObject<CategoryRes> categoryObjRes =
                 categoryService.createCategory(req);

        return new ResponseEntity<>(categoryObjRes,categoryObjRes.getStatus());
    }

    @GetMapping("/get-category-list")
    public ResponseEntity<ResponseObject<List<Category>>> getCategoryList(@NotBlank @Min(1) @RequestParam("pageNo") Integer pageNo,
                                                                          @NotBlank @Min(1) @RequestParam("limit") Integer limit){
        Pageable pageable = PageRequest.of(pageNo-1,limit);
        ResponseObject<List<Category>> res
                = categoryService.getCategoryList(pageable);
        return new ResponseEntity<>(res,res.getStatus());
    }
    @GetMapping("/get-category-detail/{id}")
    public ResponseEntity<ResponseObject<CategoryRes>> getCategoryDetail(@PathVariable("id") String id){
        ResponseObject<CategoryRes> res =  categoryService.getCategoryDetail(id);
        return new ResponseEntity<>(res,res.getStatus());
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseObject<CategoryRes>> updateCategory(@Valid
                                                                      @RequestBody UpdateCategoryReq req){
        ResponseObject<CategoryRes> res = categoryService.updateCategory(req);
        return new ResponseEntity<>(res,res.getStatus());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject<CategoryRes>> deleteCategory(@PathVariable("id") String id){
        ResponseObject<CategoryRes> res = categoryService.deleteCategory(id);

        return new ResponseEntity<>(res,res.getStatus());
    }


}
