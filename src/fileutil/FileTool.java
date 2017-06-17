package fileutil;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mee on 2017/6/10.
 * <p>
 * 文件夹以及相关文件路径配置工具类
 */
public class FileTool {
    //获取文件夹下面的所有文件信息
    public static void getFileInfoMap(String userDir, Map<String, Double> fileMap) {
        File files = new File(userDir);
        if (files.exists()) {
            File[] fileList = files.listFiles();
            DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
            df.applyPattern(".###");
            for (File file : fileList) {
                if (!file.isDirectory()) {
                    String filePath = file.getAbsolutePath();
                    String fileName = filePath
                            .substring(filePath.lastIndexOf(File.separator) + 1, filePath.length());
                    Double fileSize = Double.parseDouble(df.format(file.length() / (1024.0 * 1024.0)));
                    fileMap.put(fileName, fileSize);
                }
            }
        }
        //System.out.println("文件夹不存在");
    }

    //创建文件夹
    public static void makeDir(String userDir) {
        File file = new File(userDir);
        if (!file.exists() && !file.isDirectory()) {
            //System.out.println("文件夹不存在，已经创建");
            file.mkdirs();
        } else {
            //System.out.println("文件夹存在，不需要创建");
        }
    }

    //获取web应用的根路径
    public static String getWebRootPath() throws Exception {
        ActionContext actionContext = ActionContext.getContext();
        ServletContext servletContext = (ServletContext) actionContext.get(ServletActionContext.SERVLET_CONTEXT);
        String webRootPath = servletContext.getRealPath(File.separator);
        return webRootPath;
    }
}
