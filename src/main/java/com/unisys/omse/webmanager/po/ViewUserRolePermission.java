package com.unisys.omse.webmanager.po;

import java.io.Serializable;

public class ViewUserRolePermission implements Serializable {
    private int userId;
    private String num;
    private String userName;
    private int roleId;
    private String roleName;
    private int permissionId;
    private String permissionName;
    private String accessUrl;
    private String method;
    private String permissionDescription;
    private String userPassword;
    //1
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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


    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }
    //2
    public ViewUserRolePermission() {
    }
    //3

    public ViewUserRolePermission(int userId,String num, String userName, int roleId, String roleName, int permissionId, String permissionName, String accessUrl, String method, String permissionDescription) {
        this.userId = userId;
        this.num = num;
        this.userName = userName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.accessUrl = accessUrl;
        this.method = method;
        this.permissionDescription = permissionDescription;
    }
    //4

    @Override
    public String toString() {
        return "ViewUserRolePermission{" +
                "userId=" + userId +
                ", num=" + num +
                ", userName='" + userName + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", accessUrl='" + accessUrl + '\'' +
                ", method='" + method + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}

