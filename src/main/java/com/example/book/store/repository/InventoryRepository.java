package com.example.book.store.repository;

import com.example.book.store.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface InventoryRepository extends JpaRepository<Inventory,String> {
    List<Inventory> findByProductIdIn(Set<String> keySet);
}
