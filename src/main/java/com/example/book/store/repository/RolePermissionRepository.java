package com.example.book.store.repository;

import com.example.book.store.entities.Permission;
import com.example.book.store.entities.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission,String> {
    @Query("select distinct r from RolePermission  r where r.roleId IN (:roleSet)")
    List<RolePermission> getRolePermissionList(List<String> roleSet);
    @Query("select p from Permission  p where p.id IN :permissionSet")
    List<Permission> getPermissionList(Set<String> permissionSet);
}

