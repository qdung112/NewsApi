package com.news.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "permission")
@Entity
public class PermissionEntity extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "permissionp")
    private List<RolePermissionEntity> rolePermissions = new ArrayList<>();

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

    public List<RolePermissionEntity> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermissionEntity> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
