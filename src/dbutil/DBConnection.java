package dbutil;

/**
 * Created by mee on 2017/6/8.
 *
 * 数据库访问类
 * 提供数据库连接的初始化等操作
 * 提供关闭资源的相关操作
 */

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public final class DBConnection {
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASSWD;
    private static Properties pr = new Properties();

    //0.从配置文件读取配置信息连接数据库
    static {
        try {
            pr.load(DBConnection.class.getClassLoader().getResourceAsStream("db.properties"));
            DRIVER = pr.getProperty("DRIVER");
            URL = pr.getProperty("URL");
            USER = pr.getProperty("USER");
            PASSWD = pr.getProperty("PASSWD");
        } catch (IOException e) {
            System.out.println("加载资源文件错误");
            e.printStackTrace();
        }
    }

    //1.注册驱动
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("注册驱动失败！");
            e.printStackTrace();
        }
    }

    //2.获得连接
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWD);
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return conn;
    }

    //3.关闭连接
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭连接失败");
                e.printStackTrace();
            }
        }
    }

    //4.关闭执行对象
    public static void closePreparedStatement(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //5.关闭结果集
    public static void closeResult(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
