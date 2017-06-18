package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import fileutil.FileTool;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mee on 2017/6/18.
 * 文件删除的action，处理文件删除逻辑
 * 接受来自云盘页面的所需要删除的文件名作为参数
 */
public class DeleteAction extends ActionSupport {
    private String deleteFile;
    //所需要删除的文件目录路径（不包括文件本身），在配置文件中配置
    private String fileDirPath;
    private Map<String, Double> fileMap = new HashMap<>();

    public Map<String, Double> getFileMap() {
        return fileMap;
    }

    public void setFileMap(Map<String, Double> fileMap) {
        this.fileMap = fileMap;
    }

    public String getFileDirPath() {
        return fileDirPath;
    }

    public void setFileDirPath(String fileDirPath) {
        this.fileDirPath = fileDirPath;
    }

    public String getDeleteFile() {
        return deleteFile;
    }

    public void setDeleteFile(String deleteFile) {
        this.deleteFile = deleteFile;
    }

    @Override
    public String execute() throws Exception {
        String userDir = FileTool.getWebRootPath()
                + fileDirPath + ActionContext.getContext().getSession().get("username");
        String fileRealPath = userDir + File.separator + deleteFile.trim();
        System.out.println(fileRealPath);
        if (FileTool.deleteFile(fileRealPath)) {
            //获取用户文件夹下面的所有文件,存放在map中
            FileTool.makeDir(userDir);
            FileTool.getFileInfoMap(userDir, fileMap);
            return SUCCESS;
        }
        return INPUT;
    }
}
