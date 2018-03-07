package com.unisys.omse.webmanager.service;

import com.unisys.omse.webmanager.po.TblNode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NodeService {
    public int nodeInsert(TblNode tblNode);
    public int nodeDelete(int id);
    public int nodeUpdate(TblNode tblNode);
    public TblNode nodeSelectOne(TblNode tblNode);
    public List<TblNode> nodeSelectAll(int whichNum);
}

