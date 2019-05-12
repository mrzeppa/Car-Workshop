package com.javawebtutor.Models;

import javax.persistence.*;

@Entity
@Table(name = "roles")

public class Roles {
    @Id
    @Column(name = "roleId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "roleName")
    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
