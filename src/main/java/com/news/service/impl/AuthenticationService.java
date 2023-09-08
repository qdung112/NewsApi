package com.news.service.impl;

import com.news.converter.TokenConverter;
import com.news.converter.UserConverter;
import com.news.dto.CustomUser;
import com.news.dto.TokenDTO;
import com.news.entity.UserEntity;
import com.news.enums.Token;
import com.news.enums.User;
import com.news.payload.request.LoginRequest;
import com.news.payload.request.RegisterRequest;
import com.news.payload.response.LoginResponse;
import com.news.payload.response.RegisterResponse;
import com.news.repository.RoleRepository;
import com.news.repository.TokenRepository;
import com.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenConverter tokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*@Value("${application.security.jwt.secret-key}")
    private String secretKey;*/
    @Value("${security.jwt.expiration}")
    private int jwtExpiration;
    /*@Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;*/


    public LoginResponse login(LoginRequest request){
        UserEntity entity = userRepository.findByUserNameAndPassword(request.getUsername(),request.getPassword());
        if(entity == null){
            throw new UsernameNotFoundException("user not found");
        }
        CustomUser savedUser = userConverter.toCustomUser(entity);
        String jwtToken = jwtService.generateToken(savedUser);
        //var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .expires_in(jwtExpiration)
                .token_type(Token.BEARER.toString())
                .build();
    }

    public RegisterResponse register(RegisterRequest request){
        UserEntity entity = userRepository.findByUserName(request.getUsername());
        if(entity != null){
            return RegisterResponse.builder().message("The username has already been taken").build();
        } if(request.getEmail() == null || request.getUsername() == null || request.getRoleCode() == null || request.getPassword() == null) {
            return RegisterResponse.builder().message("Validation Error").build();
        } else {
            UserEntity userReg = new UserEntity();
            userReg.setUserName(request.getUsername());
            userReg.setPassword(passwordEncoder.encode(request.getPassword()));
            userReg.setEmail(request.getEmail());
            userReg.setPassword(request.getPassword());
            userReg.setStatus(User.ACTIVE_STATUS.getValue());
            userReg.setRole(roleRepository.findOneByCode(request.getRoleCode()));
            userRepository.save(userReg);
            return RegisterResponse.builder().message("Validation Success").build();
        }
    }

    private void saveUserToken(CustomUser user, String jwtToken) {
        TokenDTO token = new TokenDTO();
        token.setToken(jwtToken);
        token.setToken_type(Token.BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        token.setUser_id(user.getId());
        tokenRepository.save(tokenConverter.toEntity(token));
    }
}
