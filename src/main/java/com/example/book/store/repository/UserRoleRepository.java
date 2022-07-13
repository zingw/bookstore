package com.example.book.store.repository;

import com.example.book.store.entities.User;
import com.example.book.store.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {
    @Query("select distinct (u.roleId) from UserRole  u where u.userId =?1")
    List<String> findRoleIdsByUserId(String userId);
}
