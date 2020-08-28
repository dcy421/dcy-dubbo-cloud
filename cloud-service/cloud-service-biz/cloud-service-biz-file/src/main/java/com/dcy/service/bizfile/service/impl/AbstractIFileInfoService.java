package com.dcy.service.bizfile.service.impl;

import com.dcy.db.base.service.impl.BaseServiceImpl;
import com.dcy.service.bizfile.mapper.FileInfoMapper;
import com.dcy.service.bizfile.model.FileInfo;
import com.dcy.service.bizfile.service.IFileInfoService;
import com.dcy.service.bizfile.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2019-09-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public abstract class AbstractIFileInfoService extends BaseServiceImpl<FileInfoMapper, FileInfo> implements IFileInfoService {

    private static final String FILE_SPLIT = ".";

    @Autowired
    private FileInfoMapper fileInfoMapper;


    @Override
    public FileInfo upload(MultipartFile file) throws Exception {
        return uploadCommonFile(file);
    }

    private FileInfo uploadCommonFile(MultipartFile file) throws Exception {
        FileInfo fileInfo = FileUtil.getFileInfo(file);
        FileInfo oldFileInfo = fileInfoMapper.selectById(fileInfo.getId());
        if (oldFileInfo != null) {
            return oldFileInfo;
        }
        if (!fileInfo.getName().contains(FILE_SPLIT)) {
            throw new IllegalArgumentException("缺少后缀名");
        }
        uploadFile(file, fileInfo);
        // 设置文件来源
        fileInfo.setSource(fileType());
        // 将文件信息保存到数据库
        fileInfoMapper.insert(fileInfo);
        return fileInfo;
    }

    @Override
    public void deleteFile(String id) {
        FileInfo fileInfo = fileInfoMapper.selectById(id);
        if (fileInfo != null) {
            fileInfoMapper.deleteById(fileInfo.getId());
            this.deleteFile(fileInfo);
        }
    }

    @Override
    public List<FileInfo> uploadFiles(MultipartFile[] files) {
        List<FileInfo> fileInfos = new ArrayList<>();
        Arrays.asList(files).stream().forEach(multipartFile -> {
            try {
                FileInfo fileInfo = uploadCommonFile(multipartFile);
                fileInfos.add(fileInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return fileInfos;
    }

    @Override
    public void downLoad(String fileName,String filePath, HttpServletResponse response) {
        downLoadFile(fileName,filePath,response);
    }


    /**
     * 文件来源
     *
     * @return
     */
    protected abstract String fileType();

    /**
     * 上传文件
     *
     * @param file
     * @param fileInfo
     */
    protected abstract void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception;


    /**
     * 删除文件资源
     *
     * @param fileInfo
     * @return
     */
    protected abstract boolean deleteFile(FileInfo fileInfo);

    /**
     * 下载文件
     * @param fileName
     * @param filePath
     * @param response
     * @return
     */
    protected abstract void downLoadFile(String fileName,String filePath, HttpServletResponse response);

}
