package com.news.repository;

import com.news.entity.NewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface NewRepository extends JpaRepository<NewEntity,Long> {

    List<NewEntity> findByTitleLikeOrShortDescriptionLike(String  title,String shortDescription,Pageable pageable);

}
