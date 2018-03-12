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
    @Update("update tblUsers set userName=#{userName},password=#{password},tel=#{tel},num=#{num},sex=#{sex},roleId=#{roleId}," +
            "groupId=#{groupId},isDeleted=#{isDeleted},createDate=#{createDate},remark=#{remark} where id=#{id}")
    public int userUpdate(ViewUser viewUser);
    //3selectOne
    @Select("select * from viewUsers where id =#{id} limit 1")
    public ViewUser userSelectOne(ViewUser viewUser);
    //4selectAll 需满足分页
    @Select("select * from viewUsers where isDeleted=0 order by id desc limit #{whichNum},10")
    public List<ViewUser> userSelectALL(int whichNum);
    //登陆验证
    @Select("select count(*) from tblUsers where num =#{num} and password=#{password}")
    public int userLogin(ViewUser viewUser);
    //searchAll 需满足分页
    @Select("select * from tblUsers where isDeleted=0 and userName=#{userName} order by id desc limit #{whichNum},10")
    public List<ViewUser> userSearchALL(ViewUser viewUser, int whichNum);
}
