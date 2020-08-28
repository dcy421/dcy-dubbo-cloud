package com.dcy.service.bizfile.config;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.dcy.service.bizfile.model.FileInfo;
import com.dcy.service.bizfile.properties.FileServerProperties;
import com.dcy.service.bizfile.service.impl.AbstractIFileInfoService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Author：dcy
 * @Description: FastDFS配置
 * @Date: 2019/9/18 13:59
 */
@Configuration
@ConditionalOnProperty(name = "dcy.file-server.type", havingValue = "fastdfs")
public class FastdfsAutoConfigure {

    @Autowired
    private FileServerProperties fileProperties;


    @Service
    public class FastdfsServiceImpl extends AbstractIFileInfoService {
        @Autowired
        private FastFileStorageClient storageClient;

        @Override
        protected String fileType() {
            return fileProperties.getType();
        }

        /**
         * 上传图片并且生成缩略图
         * 支持的图片格式包括"JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
         * 缩略图为上传文件名+缩略图后缀 _150x150,如 xxx.jpg,缩略图为 xxx_150x150.jpg
         * 实际样例如下
         *  原图   http://localhost:8098/M00/00/17/rBEAAl33pQaAWNQNAAHYvQQn-YE374.jpg
         *  缩略图 http://localhost:8098/M00/00/17/rBEAAl33pQaAWNQNAAHYvQQn-YE374_150x150.jpg
         * @param file
         * @param fileInfo
         * @throws Exception
         */
        @Override
        protected void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception {
            StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            fileInfo.setUrl("http://" + fileProperties.getFdfs().getWebUrl() + "/" + storePath.getFullPath());
            fileInfo.setPath(storePath.getFullPath());
        }

        /**
         * 删除文件
         * @param fileInfo
         * @return
         */
        @Override
        protected boolean deleteFile(FileInfo fileInfo) {
            if (fileInfo != null && StrUtil.isNotBlank(fileInfo.getPath())) {
                StorePath storePath = StorePath.parseFromUrl(fileInfo.getPath());
                storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            }
            return true;
        }

        /**
         * 下载文件
         * @param fileName
         * @param filePath
         * @param response
         */
        @Override
        protected void downLoadFile(String fileName,String filePath, HttpServletResponse response) {
            if (StrUtil.isNotBlank(fileName) && StrUtil.isNotBlank(filePath)) {
                StorePath storePath = StorePath.parseFromUrl(filePath);
                DownloadByteArray callback = new DownloadByteArray();
                byte[] bytes = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), callback);
                try {
                    response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                    response.setCharacterEncoding("UTF-8");
                    IoUtil.write(response.getOutputStream(),true,bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
