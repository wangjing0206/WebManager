package com.unisys.omse.webmanager.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Role {
    private int id;
    private String roleName;
    @JSONField(format="yyyy-MM-dd hh:mm:ss")
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Role() {
    }

    public Role(String roleName, Date createDate) {
        this.roleName = roleName;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
