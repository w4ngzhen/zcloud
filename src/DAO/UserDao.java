package DAO;

import bean.User;

/**
 * Created by mee on 2017/6/8.
 *
 * User DAO 接口
 *
 */
public interface UserDao {
    //登录操作
    User userLogin(String username, String password);
    //注册操作
    boolean userRegister(User user);
    //重复性验证
    boolean userRepeat(String userName);
}
