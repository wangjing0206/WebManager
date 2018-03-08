package com.unisys.omse.webmanager.service.impl;

import com.unisys.omse.webmanager.dao.NodeDAO;
import com.unisys.omse.webmanager.po.TblNode;
import com.unisys.omse.webmanager.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeServiceImpl implements NodeService{

    @Autowired
    private NodeDAO nodeDAO;

    @Override
    public int nodeInsert(TblNode tblNode) {
        return nodeDAO.nodeInsert(tblNode);
    }

    @Override
    public int nodeDelete(int id) {
        return nodeDAO.nodeDelete(id);
    }

    @Override
    public int nodeUpdate(TblNode tblNode) {
        return nodeDAO.nodeUpdate(tblNode);
    }

    @Override
    public TblNode nodeSelectOne(TblNode tblNode) {
        return nodeDAO.nodeSelectOne(tblNode);
    }

    @Override
    public List<TblNode> nodeSelectAll(int whichNum) {
        whichNum=whichNum < 1? 1:whichNum;
        return nodeDAO.nodeSelectAll(whichNum);
    }
}
