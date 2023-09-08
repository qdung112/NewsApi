package com.news.entity;

import com.news.enums.Token;
import javax.persistence.*;

@Table(name = "token")
@Entity
public class TokenEntity extends BaseEntity{

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public Token tokenType = Token.BEARER;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token getTokenType() {
        return tokenType;
    }

    public void setTokenType(Token tokenType) {
        this.tokenType = tokenType;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
