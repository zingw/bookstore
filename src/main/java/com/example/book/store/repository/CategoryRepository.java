package com.example.book.store.repository;

import com.example.book.store.entities.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    List<Category> findAllByIsDeletedIsFalse(Pageable pageable);

    Optional<Category> findByIdAndIsDeletedIsFalse(String categoryId);
}