package com.news.repository;

import com.news.entity.RoleEntity;
import com.news.entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity,Long> {
    List<RolePermissionEntity> findAllByRolep(RoleEntity role);
}
