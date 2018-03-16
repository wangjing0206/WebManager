package com.unisys.omse.webmanager.service.impl;

import com.unisys.omse.webmanager.dao.UserDao;
import com.unisys.omse.webmanager.po.Count;
import com.unisys.omse.webmanager.po.ViewUser;
import com.unisys.omse.webmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int userInsert(ViewUser viewUser) {
        return userDao.userInsert(viewUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int userDelete(int id) {
        return userDao.userDelete(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int userUpdate(ViewUser viewUser) {
        return userDao.userUpdate(viewUser);
    }

    @Override
    public ViewUser userSelectOne(ViewUser viewUser) {
        return userDao.userSelectOne(viewUser);
    }

    @Override
    public ViewUser userSelectByNum(String num) {
        return userDao.userSelectByNum(num);
    }

    @Override
    public List<ViewUser> userSelectAll(int whichNum) {
        whichNum=whichNum<1 ? 1 : whichNum;
        return userDao.userSelectALL((whichNum-1)*10);
    }

    @Override
    public int userLogin(ViewUser viewUser) {
        return userDao.userLogin(viewUser);
    }

    @Override
    public List<ViewUser> userSearchALL(ViewUser viewUser, int whichNum) {
        whichNum=whichNum<1 ? 1 : whichNum;
        return userDao.userSearchALL(viewUser,(whichNum-1)*10);
    }

    @Override
    public List<Count> getCount() {
        return userDao.getCount();
    }

    @Override
    public int getCountForSearch(ViewUser viewUser) {
        return userDao.getCountForSearch(viewUser);
    }
}
