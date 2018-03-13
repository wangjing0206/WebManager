package com.unisys.omse.webmanager.service;

import com.unisys.omse.webmanager.po.ViewUserRolePermission;
import java.util.List;

public interface UserRolePermissionService {

    public List<ViewUserRolePermission> UserRolePermissionSelectAll(String num);
}
