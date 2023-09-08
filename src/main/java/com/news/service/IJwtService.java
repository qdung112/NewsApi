package com.news.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtService {
    String extractUsername(String jwt);
    boolean isTokenValid(String token, UserDetails userDetails);
}
