package com.news.converter;

import com.news.dto.CustomUser;
import com.news.dto.RoleDTO;
import com.news.entity.RoleEntity;
import com.news.entity.UserEntity;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {
    public CustomUser toCustomUser(UserEntity entity){
        CustomUser result = new CustomUser(entity.getUserName(), entity.getPassword(), true,true,true,true,null);
        result.setId(entity.getId());
        result.setEmail(entity.getEmail());
        return result;
    }

    public UserEntity toEntity(CustomUser user) {
        UserEntity result = new UserEntity();
        result.setUserName(user.getUsername());
        result.setPassword(user.getPassword());
        result.setEmail(user.getEmail());
        return result;
    }
}
