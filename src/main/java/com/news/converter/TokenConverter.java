package com.news.converter;

import com.news.dto.TokenDTO;
import com.news.entity.TokenEntity;
import org.springframework.stereotype.Component;

@Component
public class TokenConverter {

    public TokenEntity toEntity(TokenDTO tokenDTO){
        TokenEntity entity = new TokenEntity();
        entity.setToken(tokenDTO.getToken());
        entity.setTokenType(tokenDTO.getToken_type());
        return entity;
    }
}
