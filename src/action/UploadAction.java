package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fileutil.FileTool;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by mee on 2017/6/6.
 * 上传的action,用以文件上传时进行相关的操作
 * 需要一个拦截器用以拦截验证
 * 在每次上传的时候只能是登录的才能够进行上传
 * 否则跳转到登录页面
 */

public class UploadAction extends ActionSupport {
    private File upload;
    private String uploadContentType;
    private String uploadFileName;
    private String savePath;

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    @Override
    public String execute() throws Exception {
        FileInputStream fis = new FileInputStream(getUpload());
        String userDir = FileTool.getWebRootPath()
                + savePath + ActionContext.getContext().getSession().get("username");
        FileTool.makeDir(userDir);
        FileOutputStream fos = new FileOutputStream(userDir + File.separator + uploadFileName);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        setUploadFileName(uploadFileName);
        return SUCCESS;
    }
}
