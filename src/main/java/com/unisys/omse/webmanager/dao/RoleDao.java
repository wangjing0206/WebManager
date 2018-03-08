package com.unisys.omse.webmanager.dao;

import com.unisys.omse.webmanager.po.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleDao {
    //0insert
    @Insert("insert into tblRoles(roleName,createDate,remark)"
            +"values(#{roleName},#{createDate},#{remark})")
    public int roleInsert(Role role);
    //1delete
    @Delete("delete from tblRoles where id =#{id} limit 1")
    public int roleDelete(int id);
    //2update
    @Update("update tblRoles set roleName=#{roleName},createDate=#{createDate},remark=#{remark} where id=#{id}")
    public int roleUpdate(Role  role);
    //3selectOne
    @Select("select * from tblRoles where id =#{id} limit 1")
    public Role roleSelectOne(Role  role);
    //4selectAll 需满足分页
    @Select("select * from tblRoles order by id desc limit #{whichNum},10")
    public List<Role> roleSelectALL(int whichNum);
}
