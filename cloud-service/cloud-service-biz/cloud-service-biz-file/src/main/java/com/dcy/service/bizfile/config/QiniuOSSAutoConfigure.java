package com.dcy.service.bizfile.config;

import cn.hutool.core.io.IoUtil;
import com.dcy.service.bizfile.model.FileInfo;
import com.dcy.service.bizfile.properties.FileServerProperties;
import com.dcy.service.bizfile.service.impl.AbstractIFileInfoService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * @Author：dcy
 * @Description: 七牛云配置
 * @Date: 2019/9/18 13:59
 */
@Configuration
@ConditionalOnProperty(name = "dcy.file-server.type", havingValue = "qiniu")
public class QiniuOSSAutoConfigure {

    @Autowired
    private FileServerProperties fileProperties;


    /**
     * 华东机房
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {
        return new com.qiniu.storage.Configuration(Zone.zone2());
    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(qiniuConfig());
    }

    /**
     * 认证信息实例
     *
     * @return
     */
    @Bean
    public Auth auth() {
        return Auth.create(fileProperties.getOss().getAccessKey(), fileProperties.getOss().getAccessKeySecret());
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth(), qiniuConfig());
    }


    @Service
    public class QiniuOssServiceImpl extends AbstractIFileInfoService {
        @Autowired
        private UploadManager uploadManager;
        @Autowired
        private BucketManager bucketManager;
        @Autowired
        private Auth auth;

        @Override
        protected String fileType() {
            return fileProperties.getType();
        }

        @Override
        protected void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception {
            // 调用put方法上传
            uploadManager.put(file.getBytes(), fileInfo.getName(), auth.uploadToken(fileProperties.getOss().getBucketName()));
            fileInfo.setUrl(fileProperties.getOss().getEndpoint() + "/" + fileInfo.getName());
            fileInfo.setPath(fileProperties.getOss().getEndpoint() + "/" + fileInfo.getName());
        }

        @Override
        protected boolean deleteFile(FileInfo fileInfo) {
            try {
                Response response = bucketManager.delete(fileProperties.getOss().getBucketName(), fileInfo.getPath());
                int retry = 0;
                while (response.needRetry() && retry++ < 3) {
                    response = bucketManager.delete(fileProperties.getOss().getBucketName(), fileInfo.getPath());
                }
            } catch (QiniuException e) {
                return false;
            }
            return true;
        }

        @Override
        protected void downLoadFile(String fileName, String filePath, HttpServletResponse response) {
            // 获取下载文件路径
            String targetUrl = fileProperties.getOss().getEndpoint() + "/" + fileName;
            String url = auth.privateDownloadUrl(targetUrl);
            OkHttpClient client = new OkHttpClient();
            Request req = new Request.Builder().url(url).build();
            try {
                okhttp3.Response resp = client.newCall(req).execute();
                if(resp.isSuccessful()) {
                    ResponseBody body = resp.body();
                    InputStream is = body.byteStream();
                    response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                    response.setCharacterEncoding("UTF-8");
                    IoUtil.write(response.getOutputStream(),true,IoUtil.readBytes(is));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
