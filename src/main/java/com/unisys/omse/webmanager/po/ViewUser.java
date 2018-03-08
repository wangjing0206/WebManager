package com.unisys.omse.webmanager.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class ViewUser {
    //0定义字段
    private int id;
    private String userName;
    private String password;
    private String tel;
    private String num;
    private int sex;
    private int roleId;
    private int groupId;
    private int isDeleted;
    @JSONField(format="yyyy-MM-dd hh:mm:ss")
    private Date createDate;
    private String remark;
    private String roleName;
    private String groupName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ViewUser() {
    }

    public ViewUser( String userName, String password, String tel, String num, int sex, int roleId, int groupId, int isDeleted, Date createDate, String remark) {
        this.userName = userName;
        this.password = password;
        this.tel = tel;
        this.num = num;
        this.sex = sex;
        this.roleId = roleId;
        this.groupId = groupId;
        this.isDeleted = isDeleted;
        this.createDate = createDate;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ViewUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", num='" + num + '\'' +
                ", sex=" + sex +
                ", roleId=" + roleId +
                ", groupId=" + groupId +
                ", isDeleted=" + isDeleted +
                ", createDate=" + createDate +
                ", remark='" + remark + '\'' +
                ", roleName='" + roleName + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}

