package com.example.book.store.repository;

import com.example.book.store.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail,String> {
}
