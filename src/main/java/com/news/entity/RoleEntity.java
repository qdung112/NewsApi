package com.news.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users = new ArrayList<>();

    @OneToMany(mappedBy = "rolep")
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<RolePermissionEntity> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermissionEntity> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}