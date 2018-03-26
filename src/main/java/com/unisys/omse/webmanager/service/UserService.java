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
    public ViewUser userSelectByNum(String num);
    //4selectAll
    public List<ViewUser> userSelectAll(int whichNum);
    //5登录验证
    public int userLogin(ViewUser viewUser);
    //6模糊查询
    public List<ViewUser> userSearchALL(ViewUser viewUser, int whichNum);
    //7获取总条数
    public List<Count> getCount();
    //8获取总条数（模糊查询）
    public int getCountForSearch(ViewUser viewUser);
    //8查询工号是否存在
    public int checkNum(String num);
}
