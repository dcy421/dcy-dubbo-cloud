package com.dcy.file.biz.service;

import com.dcy.db.base.service.BaseService;
import com.dcy.file.biz.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dcy
 * @since 2019-09-18
 */
public interface IFileInfoService extends BaseService<FileInfo> {

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    FileInfo upload(MultipartFile file) throws Exception;


    /**
     *  删除文件 （暂时不用）
     * @param id
     */
    void deleteFile(String id);

    /**
     * 批量上传文件
     * @param files
     * @return
     */
    List<FileInfo> uploadFiles(MultipartFile[] files);

    /**
     * 下载文件
     * @param fileName
     * @param filePath
     * @param response
     */
    void downLoad(String fileName,String filePath, HttpServletResponse response);
}
