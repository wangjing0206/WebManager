package com.unisys.omse.webmanager.service;

import com.unisys.omse.webmanager.po.ViewUser;

import java.util.List;

public interface UserService {
    //0insert
    public int userInsert(ViewUser viewUser);
    //1delete
    public int userDelete(int id);
    //2update
    public int userUpdate(ViewUser viewUser);
    //3selectOne
    public ViewUser userSelectOne(ViewUser viewUser);
    //4selectAll
    public List<ViewUser> userSelectAll(int whichNum);
}
