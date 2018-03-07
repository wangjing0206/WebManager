package com.unisys.omse.webmanager.dao;

import com.unisys.omse.webmanager.po.TblNode;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NodeDAO {

    @Insert("insert into tblNode(nodeid,nodename,configid,ipaddress,status,laststarttime,laststoptime) "+
            "values (#{nodeid},#{nodename},#{configid},#{ipaddress},#{status},#{laststarttime},#{laststoptime})")
    public int nodeInsert(TblNode tblNode);

    @Delete("delete from tblNode where id = #{id} limit 1")
    public int nodeDelete(int id);

    @Update("update tblNode set configid=#{configid},status=#{status},laststarttime=#{laststarttime}," +
            "laststoptime=#{laststoptime} where id=#{id}")
    public int nodeUpdate(TblNode tblNode);

    @Select("select * from tblNode where id=#{id} limit 1")
    public TblNode nodeSelectOne(TblNode tblNode);

    //满足分页的要求
    @Select("select * from tblNode order by id desc limit #{whichNum},10")
    public List<TblNode> nodeSelectAll(int whichNum);

}
