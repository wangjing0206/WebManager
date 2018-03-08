package com.unisys.omse.webmanager.service.serviceImpl;

import com.unisys.omse.webmanager.dao.RoleDao;
import com.unisys.omse.webmanager.po.Role;
import com.unisys.omse.webmanager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;

    @Override
    public int roleInsert(Role role) {
        return roleDao.roleInsert(role);
    }

    @Override
    public int roleDelete(int id) {
        return roleDao.roleDelete(id);
    }

    @Override
    public int roleUpdate(Role role) {
        return roleDao.roleUpdate(role);
    }

    @Override
    public Role roleSelectOne(Role role) {
        return roleDao.roleSelectOne(role);
    }

    @Override
    public List<Role> roleSelectAll(int whichNum) {
        return roleDao.roleSelectALL((whichNum-1)*10);
    }
}
