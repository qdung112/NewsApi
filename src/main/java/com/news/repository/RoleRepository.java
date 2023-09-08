package com.news.repository;

import com.news.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findOneByCode(String code);
    RoleEntity findOneById(long id);

}
