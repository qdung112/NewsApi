package com.news.service.impl;

import com.news.converter.UserConverter;
import com.news.dto.CustomUser;
import com.news.entity.RolePermissionEntity;
import com.news.entity.UserEntity;
import com.news.repository.PermissionRepository;
import com.news.repository.RolePermissionRepository;
import com.news.repository.RoleRepository;
import com.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.news.enums.User.ACTIVE_STATUS;


@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserNameAndStatus(name,ACTIVE_STATUS.getValue());
        if(userEntity == null){
            throw new UsernameNotFoundException("user not found");
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<RolePermissionEntity> rp = rolePermissionRepository.findAllByRolep(roleRepository.findOneByCode(userEntity.getRole().getCode()));
        for(RolePermissionEntity role : rp){
            authorityList.add(new SimpleGrantedAuthority(permissionRepository.findOneById(role.getPermissionp().getId()).getCode()));
        }
        CustomUser user = new CustomUser(userEntity.getUserName(),userEntity.getPassword(),true,true,true,true,authorityList);
        user.setFullName(userEntity.getFullName());
        return user;
    }

    public CustomUser register(CustomUser user){
        UserEntity entity = userConverter.toEntity(user);
        return userConverter.toCustomUser(userRepository.save(entity));
    }
}
