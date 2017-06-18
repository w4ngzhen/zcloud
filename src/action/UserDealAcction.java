package action;

import DAO.UserDao;
import DAO.UserDaoImpl;
import bean.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import fileutil.FileTool;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mee on 2017/6/6.
 * <p>
 * 用户相关的处理Action
 * 包含登录的处理、注册完成之后的向数据库注册的处理、用户的退出处理
 */
public class UserDealAcction extends ActionSupport {

    private User user;
    private UserDao userDao = new UserDaoImpl();
    private String savePath;
    private String passwdtemp;

    private Map<String, Double> fileMap = new HashMap<>();

    public Map<String, Double> getFileMap() {
        return fileMap;
    }

    public void setFileMap(Map<String, Double> fileMap) {
        this.fileMap = fileMap;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getPasswdtemp() {
        return passwdtemp;
    }

    public void setPasswdtemp(String passwdtemp) {
        this.passwdtemp = passwdtemp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //默认处理登录过程
    public String login() throws Exception {
        User checkUser = userDao.userLogin(user.getUsername(), user.getPassword());
        if (checkUser == null) {
            addFieldError("user", "登录信息错误!");
            return INPUT;
        }
        Map<String, Object> userInfo = ActionContext.getContext().getSession();//记录用户登录信息
        userInfo.put("username", user.getUsername());
        //获取用户文件夹下面的所有文件,存放在map中
        String userDir = FileTool.getWebRootPath()
                + savePath + ActionContext.getContext().getSession().get("username");
        FileTool.makeDir(userDir);
        FileTool.getFileInfoMap(userDir, fileMap);
        return SUCCESS;
    }

    //用户注册之后的action
    public String register() {
        if (userDao.userRegister(user)) {
            return SUCCESS;
        } else {
            addFieldError("user", "注册失败");
        }
        return INPUT;
    }

    //用户退出action
    //需要配置一个拦截器
    public String userQuit() {
        //销毁session代码...
        ActionContext ac = ActionContext.getContext();
        ac.getSession().remove("username");
        return SUCCESS;
    }

    //登录验证
    public void validateLogin() {
        //如果用户名为空或为空字符串，则添加错误信息
        if (user.getUsername() == null || user.getUsername().trim().equals("")) {
            //添加表单效验错误信息
            addFieldError("username", "输入的用户名不能为空");
        }
        //如果密码为空或为空字符串，则添加错误信息
        if (user.getPassword() == null || user.getPassword().trim().equals("")) {
            addFieldError("password", "输入的密码不能为空");
        }
    }
}
