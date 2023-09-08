package com.news.service;

import com.news.dto.PermissionDTO;
import com.news.enums.Permission;

import java.util.List;

public interface IPermissionService {
    List<PermissionDTO> findAll();
    PermissionDTO findOneByCode(String code);
}
