package com.unisys.omse.webmanager.service.impl;

import com.unisys.omse.webmanager.dao.UserRolePermissionDao;
import com.unisys.omse.webmanager.po.ViewUserRolePermission;
import com.unisys.omse.webmanager.service.UserRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolePermissionServiceImpl implements UserRolePermissionService {

    @Autowired
    private UserRolePermissionDao userRolePermissionDao;

    @Override
    public List<ViewUserRolePermission> UserRolePermissionSelectAll(String num) {
        return userRolePermissionDao.UserRolePermissionSelectAll(num);
    }

}
