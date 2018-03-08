package com.unisys.omse.webmanager.service.serviceImpl;

import com.unisys.omse.webmanager.dao.UserDao;
import com.unisys.omse.webmanager.po.ViewUser;
import com.unisys.omse.webmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public int userInsert(ViewUser viewUser) {
        return userDao.userInsert(viewUser);
    }

    @Override
    public int userDelete(int id) {
        return userDao.userDelete(id);
    }

    @Override
    public int userUpdate(ViewUser viewUser) {
        return userDao.userUpdate(viewUser);
    }

    @Override
    public ViewUser userSelectOne(ViewUser viewUser) {
        return userDao.userSelectOne(viewUser);
    }

    @Override
    public List<ViewUser> userSelectAll(int whichNum) {
        whichNum=whichNum<1 ? 1 : whichNum;
        return userDao.userSelectALL((whichNum-1)*10);
    }
}
