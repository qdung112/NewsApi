package com.news.service.impl;

import com.news.converter.PermissionConverter;
import com.news.dto.PermissionDTO;
import com.news.repository.PermissionRepository;
import com.news.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionConverter permissionConverter;

    @Override
    public List<PermissionDTO> findAll() {
        return permissionConverter.toListDto(permissionRepository.findAll());
    }

    @Override
    public PermissionDTO findOneByCode(String code) {
        return permissionConverter.toDto(permissionRepository.findByCode(code));
    }

}
