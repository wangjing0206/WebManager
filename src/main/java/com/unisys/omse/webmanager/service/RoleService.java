package com.unisys.omse.webmanager.service;

import com.unisys.omse.webmanager.po.Role;

import java.util.List;

public interface RoleService {
    //0insert
    public int roleInsert(Role role);
    //1delete
    public int roleDelete(int id);
    //2update
    public int roleUpdate(Role role);
    //3selectOne
    public Role roleSelectOne(Role role);
    //4selectAll
    public List<Role> roleSelectAll(int whichNum);
}
