package com.news.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "role_permission")
public class RolePermissionEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity rolep;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private PermissionEntity permissionp;

    public RoleEntity getRolep() {
        return rolep;
    }

    public void setRolep(RoleEntity rolep) {
        this.rolep = rolep;
    }

    public PermissionEntity getPermissionp() {
        return permissionp;
    }

    public void setPermissionp(PermissionEntity permissionp) {
        this.permissionp = permissionp;
    }
}
