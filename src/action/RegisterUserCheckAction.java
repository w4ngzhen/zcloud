package action;

import DAO.UserDao;
import DAO.UserDaoImpl;
import com.opensymphony.xwork2.ActionSupport;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by mee on 2017/6/10.
 *
 *  该Action作用是注册页面AJAX进行的账户名的唯一性验证
 *
 */
public class RegisterUserCheckAction extends ActionSupport {
    private String registeringName;
    private UserDao userDao = new UserDaoImpl();


    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getRegisteringName() {
        return registeringName;
    }

    public void setRegisteringName(String registeringName) {
        this.registeringName = registeringName;
    }

    @Override
    public String execute() {
        String result;
        try {
            //System.out.println(registeringName);
            if (registeringName == null) {
                result = "参数获取失败";
            } else if (userDao.userRepeat(registeringName)) {
                result = "当前用户名已存在";
            } else {
                result = "当前用户名可用";
            }
            inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
}
