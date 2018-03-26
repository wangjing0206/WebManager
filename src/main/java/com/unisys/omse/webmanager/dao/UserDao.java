package com.unisys.omse.webmanager.dao;

import com.unisys.omse.webmanager.po.Count;
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
    @Select("select * from viewUsers where id =#{id} and isDeleted=0 limit 1")
    public ViewUser userSelectOne(ViewUser viewUser);
    //通过工号查询
    @Select("select * from viewUsers where num =#{num} and isDeleted=0 limit 1")
    public ViewUser userSelectByNum(String num);
    //4selectAll 需满足分页
    @Select("select * from viewUsers where isDeleted=0 order by id desc limit #{whichNum},10")
    public List<ViewUser> userSelectALL(int whichNum);
    //5登陆验证
    @Select("select count(*) from tblUsers where isDeleted=0 and num =#{num} and password=#{password}")
    public int userLogin(ViewUser viewUser);
    //6searchAll 需满足分页todo
    @Select("select * from viewUsers where isDeleted=0 and num like #{viewUser.num} and userName like #{viewUser.userName} and " +
            "roleName like #{viewUser.roleName} and groupName like #{viewUser.groupName} order by id desc limit #{whichNum},10 ")
    public List<ViewUser> userSearchALL(@Param("viewUser") ViewUser viewUser, @Param("whichNum") int whichNum);
    //查询条数（模糊查询）
   @Select("select count(*) from viewUsers where isDeleted=0 and num like #{num} and userName like #{userName} and "+
           " roleName like #{roleName} and groupName like #{groupName}")
    public int getCountForSearch(ViewUser viewUser);
    //查询条数
    @Select("SELECT 'count' as 'id','key','总数' as 'value',count(*) as 'count' FROM viewUsers where isDeleted=0" +
            " union (SELECT sex,'sex',case sex when '1' then'男' when '0' then '女' end,count(*) FROM viewUsers  where isDeleted=0 group by sex order by sex desc)" +
            " union SELECT groupId,'groupId',groupName,count(*)  FROM viewUsers where isDeleted=0 group by groupId" +
            " union SELECT roleId,'roleId',roleName,count(*)  FROM viewUsers  where isDeleted=0 group by roleId")
    public List<Count> getCount();
    //验证工号是否存在
    @Select("select count(*) from viewUsers where num =#{num} and isDeleted=0 limit 1")
    public int chekNum(String num);
}
