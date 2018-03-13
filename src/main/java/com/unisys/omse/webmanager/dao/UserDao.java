package com.unisys.omse.webmanager.dao;

import com.unisys.omse.webmanager.po.ViewUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    //0insert
    @Insert("insert into tblUsers(userName,password,tel,num,sex,roleId,groupId,isDeleted,createDate,remark)"
            +"values(#{userName},#{password},#{tel},#{num},#{sex},#{roleId},#{groupId},#{isDeleted},#{createDate},#{remark})")
    public int userInsert(ViewUser viewUser);
    //1delete
    /*@Delete("delete from tblUsers where id =#{id} limit 1")
    public int userDelete(int id);*/
    @Update("update tblUsers set isDeleted = 1 where id =#{id}")
    public int userDelete(int id);
    //2update
    @Update("update tblUsers set userName=#{userName},tel=#{tel},num=#{num},sex=#{sex},roleId=#{roleId}," +
            "groupId=#{groupId},remark=#{remark} where id=#{id}")
    public int userUpdate(ViewUser viewUser);
    //3selectOne
    @Select("select * from viewUsers where id =#{id} limit 1")
    public ViewUser userSelectOne(ViewUser viewUser);
    //4selectAll 需满足分页
    @Select("select * from viewUsers where isDeleted=0 order by id desc limit #{whichNum},10")
    public List<ViewUser> userSelectALL(int whichNum);
    //5登陆验证
    @Select("select count(*) from tblUsers where isDeleted=0 and num =#{num} and password=#{password}")
    public int userLogin(ViewUser viewUser);
    //6searchAll 需满足分页todo
    @Select("select * from viewUsers where isDeleted=0 and num like #{num} and userName like #{userName} and " +
            "roleName like #{roleName} and groupName like #{groupName} order by id desc limit 0,10 ")
    public List<ViewUser> userSearchALL(ViewUser viewUser);
    //查询条数
    @Select("select count(*) from tblUsers where isDeleted =0")
    public int getCount();
}
