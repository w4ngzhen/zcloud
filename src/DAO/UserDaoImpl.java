package DAO;

import domain.User;
import dbutil.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mee on 2017/6/8.
 */
public class UserDaoImpl implements UserDao {
    /*
    *
    * 用户登录操作
    * 通过用户名以及密码向数据库查询相关的数据
    * 如果存在就返回实例化的用户对象
    *
    */
    @Override
    public User userLogin(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql_userCheck = "SELECT * FROM user WHERE username = ? AND password = ?";
        User user = null;
        try {
            conn = DBConnection.getConn();
            ps = conn.prepareStatement(sql_userCheck);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConn(conn);
            DBConnection.closePreparedStatement(ps);
            DBConnection.closeResult(rs);
        }
        return user;
    }


    /*
    *
    * 用户注册之后
    * 想数据库写入该用户信息
    * 写入成功返回true
    *
    * */
    @Override
    public boolean userRegister(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql_register = "INSERT INTO user VALUES (?, ?)";
        try {
            conn = DBConnection.getConn();
            ps = conn.prepareStatement(sql_register);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            if (ps.executeUpdate() > 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            DBConnection.closeConn(conn);
            DBConnection.closePreparedStatement(ps);
        }
        return false;
    }


    /*
    *
    * 用户唯一性查询
    * 通过传入的用户名向数据库查询
    * 已经存在则返回true
    *
    * */
    @Override
    public boolean userRepeat(String userName) {
        Connection conn = DBConnection.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql_check = "SELECT username FROM user WHERE username = ?";
        try {
            ps = conn.prepareStatement(sql_check);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConn(conn);
            DBConnection.closePreparedStatement(ps);
            DBConnection.closeResult(rs);
        }
        return false;
    }
}
