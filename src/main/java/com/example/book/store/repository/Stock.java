package com.example.book.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Stock extends JpaRepository<Stock,String> {
}
