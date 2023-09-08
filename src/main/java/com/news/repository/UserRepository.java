package com.news.repository;

import com.news.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserNameAndStatus(String name, int status);
    UserEntity findByUserNameAndPassword(String username, String password);

    UserEntity findByUserName(String username);
}
