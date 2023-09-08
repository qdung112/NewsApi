package com.news.service.impl;

import com.news.converter.RoleConverter;
import com.news.dto.RoleDTO;
import com.news.entity.PermissionEntity;
import com.news.entity.RoleEntity;
import com.news.entity.RolePermissionEntity;
import com.news.repository.PermissionRepository;
import com.news.repository.RolePermissionRepository;
import com.news.repository.RoleRepository;
import com.news.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Override
    public RoleDTO findOneByCode(String code) {
        RoleEntity role = roleRepository.findOneByCode(code);
        return roleConverter.toDTO(role);
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        RoleEntity entity = roleConverter.toEntity(roleDTO);
        RoleDTO result = roleConverter.toDTO(roleRepository.save(entity));
        List<RolePermissionEntity> rolePermissions = new ArrayList<>();
            for (String code : roleDTO.getPermissionsCode()) {
                RolePermissionEntity rolePermission = new RolePermissionEntity();
                rolePermission.setRolep(entity);
                rolePermission.setPermissionp(permissionRepository.findByCode(code));
                rolePermissions.add(rolePermission);
            }
            rolePermissionRepository.saveAll(rolePermissions);
            return result;
    }
}
