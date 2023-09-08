package com.news.api;

import com.news.payload.request.LoginRequest;
import com.news.payload.request.RegisterRequest;
import com.news.payload.response.LoginResponse;
import com.news.payload.response.RegisterResponse;
import com.news.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/api/auth")
public class UserAPI {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> userRegister(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

}
