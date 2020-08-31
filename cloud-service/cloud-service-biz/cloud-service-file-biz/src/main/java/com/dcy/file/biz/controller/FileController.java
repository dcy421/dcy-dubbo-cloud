package com.dcy.file.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dcy.common.model.ResponseData;
import com.dcy.file.biz.service.IFileInfoService;
import com.dcy.file.biz.model.FileInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2019/9/18 14:02
 */
@RestController
@RequestMapping("/file")
@Api(value = "FileController", tags = {"文件操作接口"})
public class FileController {

    @Autowired
    private IFileInfoService iFileInfoService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileInfo", value = "查询对象", dataType = "FileInfo", paramType = "query")
    })
    @GetMapping(value = "/page")
    public ResponseData<IPage<FileInfo>> page(FileInfo fileInfo) {
        return ResponseData.success(iFileInfoService.pageList(fileInfo));
    }

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping(value = "/upload")
    public ResponseData<FileInfo> upload(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseData.success(iFileInfoService.upload(file));
    }

    @ApiOperation(value = "批量上传文件", notes = "批量上传文件")
    @PostMapping(value = "/uploadFiles")
    public ResponseData<List<FileInfo>> uploadFiles(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        return ResponseData.success(iFileInfoService.uploadFiles(files));
    }

    @ApiOperation(value = "下载图片", notes = "下载图片")
    @PostMapping("/downLoad")
    public void downLoad(@RequestParam("fileName") String fileName,@RequestParam("filePath") String filePath, HttpServletResponse response) throws Exception {
        iFileInfoService.downLoad(fileName,filePath,response);
    }

}
