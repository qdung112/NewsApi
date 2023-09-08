package com.news.converter;

import com.news.dto.NewDTO;
import com.news.dto.PermissionDTO;
import com.news.entity.NewEntity;
import com.news.entity.PermissionEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionConverter {
    public PermissionEntity toEntity(PermissionDTO dto) {
        PermissionEntity result = new PermissionEntity();
        result.setName(dto.getName());
        result.setCode(dto.getCode());
        return result;
    }

    public PermissionDTO toDto(PermissionEntity entity) {
        PermissionDTO result = new PermissionDTO();
        result.setId(entity.getId());
        result.setCode(entity.getCode());
        result.setName(entity.getName());
        return result;
    }

    public List<PermissionEntity> toListEntities(List<PermissionDTO> dtoList){
        List<PermissionEntity> entities = new ArrayList<>();
        for(PermissionDTO dto : dtoList){
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public List<PermissionDTO> toListDto(List<PermissionEntity> entities){
        List<PermissionDTO>  dtoList = new ArrayList<>();
        for(PermissionEntity entity : entities){
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }
}
