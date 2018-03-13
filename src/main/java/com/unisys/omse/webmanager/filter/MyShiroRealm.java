package com.unisys.omse.webmanager.filter;

//import com.xxx.shiro.entity.SysPermission;
//import com.xxx.shiro.entity.SysRole;
//import com.xxx.shiro.entity.UserInfo;
//import com.xxx.shiro.service.UserInfoService;


import com.unisys.omse.webmanager.po.ViewUserRolePermission;
import com.unisys.omse.webmanager.service.UserRolePermissionService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/12/11.
 * 自定义权限匹配和账号密码匹配
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserRolePermissionService userRolePermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        ViewUserRolePermission viewUserRolePermission = (ViewUserRolePermission) principals.getPrimaryPrincipal();
        List<ViewUserRolePermission> list0= userRolePermissionService.UserRolePermissionSelectAll(viewUserRolePermission.getUserName());
        Set set0=new HashSet();
        for (int i = 0; i < list0.size() ; i++) {
            set0.add(list0.get(i).getRoleName());
            authorizationInfo.addStringPermission(list0.get(i).getPermissionName());
        }
        authorizationInfo.addRoles(set0);
//        for (SysRole role : userInfo.getRoleList()) {
//            authorizationInfo.addRole(role.getRole());
//            for (SysPermission p : role.getPermissions()) {
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        System.out.println(authorizationInfo);
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
//        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String num = (String) token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        ViewUserRolePermission viewUserRolePermission = userRolePermissionService.UserRolePermissionSelectAll(num).get(0);
//        System.out.println("----->>userInfo="+userInfo);
        if (viewUserRolePermission == null) {
            return null;
        }
//        if (userInfo.getState() == 1) { //账户冻结
//            throw new LockedAccountException();
//        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                userInfo, //用户名
//                userInfo.getPassword(), //密码
//                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
//                getName()  //realm name
                viewUserRolePermission, //用户
                viewUserRolePermission.getUserPassword(), //密码
                ByteSource.Util.bytes(num),
                num+"R"  //realm name
        );
        return authenticationInfo;
    }

}  
