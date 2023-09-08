package com.news.repository;

import com.news.entity.PermissionEntity;
import com.news.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<PermissionEntity,Long> {
    PermissionEntity findByCode(String code);
    PermissionEntity findOneById(long id);
}
