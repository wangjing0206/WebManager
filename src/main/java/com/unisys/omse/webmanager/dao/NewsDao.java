package com.unisys.omse.webmanager.dao;

import com.unisys.omse.webmanager.po.TblNews;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsDao {
    //insert
    @Insert("insert into tblnews(title,content,currentNews,createDate,updateDate)" +
            "values (#{title},#{content},#{currentNews},#{createDate},#{updateDate})")
    public int newsInsert(TblNews tblNews);

    //delete
    @Delete("delete from tblnews where id = #{id} limit 1")
    public int newsDelete(int id);

    //update
    @Update("update tblnews set title=#{title},content=#{content},currentNews=#{currentNews}," +
            "updateDate=#{updateDate} where id=#{id}")
    public int newsUpdate(TblNews tblnews);

    //selectCurrentNews
    @Select("select * from tblnews where currentNews=1 order by id desc limit 3" )
    public List<TblNews>  newsSelectCurrentNews();

    //selectAll
    @Select("select * from tblnews order by id desc limit #{whichNum},10")
    public List<TblNews> newsSelectAll(int whichNum);

    //selectById
    @Select("select * from tblnews where id=#{id} limit 1")
    public TblNews newsSelectById(int id);

}
