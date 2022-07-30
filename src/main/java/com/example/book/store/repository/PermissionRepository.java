package com.example.book.store.repository;

import com.example.book.store.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {
   @Query(value = "select distinct (p.name) from Permission  p join RolePermission  rp on " +
           " rp.permissionId = p.id join UserRole ur on ur.roleId = rp.roleId join User u on ur.userId = u.id " +
           " where p.isDeleted = false and rp.isDeleted = false  and u.isDeleted = false")
    List<String> getAuthors(String userId);
}
