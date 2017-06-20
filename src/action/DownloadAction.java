package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fileutil.FileTool;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by mee on 2017/6/12.
 * 文件下载Action，用以下载时候进行相关的操作
 * 需要配置拦截器
 * 用以在下载之前验证下载权限（是否登录）
 * 否则跳转到登录页面
 */
public class DownloadAction extends ActionSupport {
    //配置用户存储文件路径
    private String savePath;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    //从zcloud.jsp获取而来的fileName（只有一个单独的文件名）
    private String downloadFile;

    //下面的set是通过jsp过来的action参数进行set时候用的
    public void setDownloadFile(String downloadFile) {
        //设置好了所需要下载的文件名
        this.downloadFile = downloadFile.trim();
    }

    public String getDownloadFile() {
        String downloadFile = this.downloadFile;
        try {
            downloadFile = new String(downloadFile.getBytes("UTF-8"), "ISO8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadFile;
    }

    /*
    * 定义一个返回InputStream的方法
    * 该方法将作为被下载的文件的入口
    * 需要配置stream类型结果时指定inputName参数
    * inputName参数的值就是方法去掉get前缀、首字母小写的字符串
    * */
    public InputStream getInputStream() throws Exception {
        //获取转码以后的文件名
        String fileName = getDownloadFile();
        //System.out.println(fileName);
        //这里使用的是如下的流获取，所以只需要相对与web应用的相对资源路径即可
        //WEB应用->[WEB-INF->upload(该部分配置在配置文件中的savePath部分)]->用户文件夹->对应文件
        String inputPath = savePath + ActionContext.getContext().getSession().get("username")
                + File.separator + this.downloadFile;
        InputStream in = ServletActionContext.getServletContext().getResourceAsStream(inputPath);
//        当然也可以使用绝对路径来获取资源文件
//        String inputPath = FileTool.getWebRootPath()
//                + savePath
//                + ActionContext.getContext().getSession().get("username")
//                + File.separator + this.downloadFile;
//        System.out.println(inputPath);
//        InputStream in = new FileInputStream(inputPath);
//        System.out.println(inputPath);
        if (in == null)
            System.out.println("路径错误");
        return in;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
