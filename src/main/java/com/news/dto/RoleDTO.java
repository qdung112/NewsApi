package com.news.dto;


import java.util.ArrayList;
import java.util.List;

public class RoleDTO extends AbstractDTO<RoleDTO>{

    private String name;
    private String code;
    private String [] permissionsCode;

    private List<PermissionDTO> permissions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getPermissionsCode() {
        return permissionsCode;
    }

    public void setPermissionsCode(String[] permissionsCode) {
        this.permissionsCode = permissionsCode;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
