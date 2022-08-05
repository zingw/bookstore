package com.example.book.store.repository;

import com.example.book.store.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,String> {
}
