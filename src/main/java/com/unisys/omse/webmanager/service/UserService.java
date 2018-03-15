package com.unisys.omse.webmanager.service;

import com.unisys.omse.webmanager.po.Count;
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
    //5登录验证
    public int userLogin(ViewUser viewUser);
    //6模糊查询
    public List<ViewUser> userSearchALL(ViewUser viewUser, int whichNum);
    //5获取总条数
    public List<Count> getCount();
}
