package com.unisys.omse.webmanager.filter;

import com.unisys.omse.webmanager.po.ViewUserRolePermission;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
    //private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void encryptPassword(ViewUserRolePermission viewUserRolePermission) {
        //String salt=randomNumberGenerator.nextBytes().toHex();
        String newPassword = new SimpleHash(algorithmName, viewUserRolePermission.getUserPassword(),  ByteSource.Util.bytes(viewUserRolePermission.getNum()), hashIterations).toHex();
        //String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
        viewUserRolePermission.setUserPassword(newPassword);

    }
    public static void main(String[] args) {
        PasswordHelper passwordHelper = new PasswordHelper();
        ViewUserRolePermission user = new ViewUserRolePermission();
        user.setNum("20180002");
        user.setUserPassword("123");
        passwordHelper.encryptPassword(user);
        System.out.println(user);
    }
}