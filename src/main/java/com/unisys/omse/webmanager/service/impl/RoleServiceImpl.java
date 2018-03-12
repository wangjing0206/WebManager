package com.unisys.omse.webmanager.service.impl;

import com.unisys.omse.webmanager.dao.RoleDao;
import com.unisys.omse.webmanager.po.Role;
import com.unisys.omse.webmanager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int roleInsert(Role role) {
        return roleDao.roleInsert(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int roleDelete(int id) {
        return roleDao.roleDelete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
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
