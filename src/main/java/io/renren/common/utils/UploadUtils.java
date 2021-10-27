package io.renren.common.utils;

import io.renren.common.enums.ErrorMsgEnum;
import io.renren.common.exception.RRException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 上传文件到服务器本地
 * @author 汪少
 * @date 2021/4/6 13:22
 */
@Slf4j
@Component
public class UploadUtils {

    @Value("${yjs.app.domain}")
    private String domain;

    public String getSuffix(MultipartFile file) {
        if (file.isEmpty() || StringUtils.isEmpty(file.getOriginalFilename())) {
            throw new RRException(ErrorMsgEnum.FILE_EMPTY.getMsg());
        }
        String filename = file.getOriginalFilename();
        try {
            return filename.substring(filename.indexOf(".") + 1);
        } catch (Exception e) {
            throw new RRException(ErrorMsgEnum.ERROR_FILE.getMsg());
        }
    }

    public String upload(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new RRException(ErrorMsgEnum.FILE_EMPTY.getMsg());
        }
        String fileName = getFileName(file.getOriginalFilename());
        String filepath = getUploadPath(folder);
        try (BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(new File(filepath + File.separator + fileName)))) {
            out.write(file.getBytes());
            out.flush();
            return getVisitUrl(fileName, folder);
        } catch (FileNotFoundException e) {
            log.error("上传文件失败 FileNotFoundException：{}", e.getMessage());
            throw new RRException(ErrorMsgEnum.FILE_UPLOAD_ERROR.getDetailMsg(e.getMessage()));
        } catch (IOException e) {
            log.error("上传文件失败 IOException：{}", e.getMessage());
            throw new RRException(ErrorMsgEnum.FILE_UPLOAD_ERROR.getDetailMsg(e.getMessage()));
        }
    }

    /**
     * 文件名后缀前添加一个时间戳
     * @param fileName
     * @return
     */
    private String getFileName(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyymmddHHmmss");
        String nowTimeStr = sDateFormate.format(new Date());
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
        return fileName;
    }

    /**
     * 获取当前系统路径
     */
    public static String getUploadPath(String folder) {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) {
            path = new File("");
        }
        File upload = new File(path.getAbsolutePath(), folder);
        if (!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getAbsolutePath();
    }

    private String getVisitUrl(String fileName, String folder) {
        return domain + folder + "/" +  fileName;
    }
}
