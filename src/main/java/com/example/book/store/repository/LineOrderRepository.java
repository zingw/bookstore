package com.example.book.store.repository;

import com.example.book.store.entities.LineOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineOrderRepository extends JpaRepository<LineOrder,String> {
}
