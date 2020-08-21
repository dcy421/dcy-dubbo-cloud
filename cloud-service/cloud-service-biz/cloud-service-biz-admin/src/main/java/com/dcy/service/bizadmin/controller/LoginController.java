package com.dcy.service.bizadmin.controller;

import com.dcy.common.model.ResponseData;
import com.dcy.service.apiadmin.api.UserInfoService;
import com.dcy.service.apiadmin.model.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/8/19 10:41
 */
@RestController
public class LoginController {

    @DubboReference(version = "1.0.0")
    private UserInfoService userInfoService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping(value = "/getUserList")
    public ResponseData<List<UserInfo>> getUserList() {
        return ResponseData.success(userInfoService.list());
    }

    @PostMapping(value = "/save")
    public ResponseData<Boolean> save(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
        return ResponseData.success(true);
    }
}
