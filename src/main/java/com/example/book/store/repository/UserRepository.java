package com.example.book.store.repository;

import com.example.book.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);

    Optional<List<User>> findByName(String employeeName);

    Optional<User> findByEmail(String email);
}
