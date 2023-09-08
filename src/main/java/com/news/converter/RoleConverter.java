package com.news.converter;


import com.news.dto.NewDTO;
import com.news.dto.RoleDTO;
import com.news.entity.NewEntity;
import com.news.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    @Autowired
    private PermissionConverter permissionConverter;

    public RoleDTO toDTO(RoleEntity entity){
        RoleDTO result = new RoleDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setCode(entity.getCode());
        //result.setPermissions(permissionConverter.toListDto(entity.getPermissions()));
        return result;
    }

    public RoleEntity toEntity(RoleDTO dto) {
        RoleEntity result = new RoleEntity();
        result.setName(dto.getName());
        result.setCode(dto.getCode());
        //result.setPermissions(permissionConverter.toListEntities(dto.getPermissions()));
        return result;
    }

    public NewEntity toEntity(NewEntity result, NewDTO dto) {
        result.setTitle(dto.getTitle());
        result.setShortDescription(dto.getShortDescription());
        result.setContent(dto.getContent());
        result.setThumbnail(dto.getThumbnail());
        return result;
    }

}
