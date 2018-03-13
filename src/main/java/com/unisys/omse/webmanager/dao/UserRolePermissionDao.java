package com.unisys.omse.webmanager.dao;


import com.unisys.omse.webmanager.po.ViewUserRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRolePermissionDao {
    @Select("select * from viewuserrolepermission where num = #{num}")
    public List<ViewUserRolePermission> UserRolePermissionSelectAll(String num);
}
