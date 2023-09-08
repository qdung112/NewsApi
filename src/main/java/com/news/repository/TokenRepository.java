package com.news.repository;

import com.news.entity.TokenEntity;
import com.news.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity,Long> {

}
