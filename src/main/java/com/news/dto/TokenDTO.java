package com.news.dto;

import com.news.enums.Token;

public class TokenDTO extends AbstractDTO<TokenDTO>{
    private String token;

    public boolean revoked;

    public boolean expired;

    private Token token_type;

    private long user_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Token getToken_type() {
        return token_type;
    }

    public void setToken_type(Token token_type) {
        this.token_type = token_type;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
