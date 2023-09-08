package com.news.service;

import com.news.dto.RoleDTO;
import com.news.entity.RoleEntity;

public interface IRoleService {
    RoleDTO findOneByCode(String code);

    RoleDTO save(RoleDTO roleDTO);
}
