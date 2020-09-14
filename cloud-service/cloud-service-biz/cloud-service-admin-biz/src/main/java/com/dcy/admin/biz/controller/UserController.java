package com.dcy.admin.biz.controller;

import com.dcy.admin.api.api.UserInfoService;
import com.dcy.admin.api.dto.UserInfoRoleDTO;
import com.dcy.admin.api.model.Role;
import com.dcy.admin.api.model.UserInfo;
import com.dcy.common.model.ResponseData;
import com.dcy.db.base.controller.BaseController;
import com.dcy.db.base.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/8/26 9:09
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController", tags = {"用户操作接口"})
public class UserController extends BaseController<UserInfo> {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @DubboReference(version = "1.0.0")
    private UserInfoService userInfoService;

    @Override
    public <T extends BaseService<UserInfo>> T getService() {
        return (T) userInfoService;
    }

    @Override
    public ResponseData<Boolean> save(UserInfo userInfo) {
        userInfo.setPassword("{bcrypt}" + passwordEncoder.encode(userInfo.getPassword()));
        return ResponseData.success(userInfoService.save(userInfo));
    }

    @ApiOperation(value = "根据用户名获取用户信息", notes = "根据用户名获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/getUserInfoByUsername")
    public ResponseData<UserInfo> getUserInfoByUsername(@RequestParam String username) {
        return ResponseData.success(userInfoService.getUserInfoByUsername(username));
    }

    @ApiOperation(value = "重置密码", notes = "重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "UserInfo", name = "userInfo", value = "对象参数", required = true)
    })
    @PostMapping(value = "/resetPassword")
    public ResponseData<Boolean> resetPassword(@RequestBody UserInfo userInfo) {
        userInfo.setPassword("{bcrypt}" + passwordEncoder.encode(userInfo.getPassword()));
        return ResponseData.success(userInfoService.updateById(userInfo));
    }

    @ApiOperation(value = "获取已授权的角色列表", notes = "根据用户id查询已授权的角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/getAuthRoleListByUserId")
    public ResponseData<List<Role>> getAuthRoleListByUserId(String userId) {
        return ResponseData.success(userInfoService.getAuthRoleListByUserId(userId));
    }

    @ApiOperation(value = "保存授权角色", notes = "保存授权角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "UserInfoRoleDto", name = "userInfoRoleDto", value = "授权角色对象参数", required = true)
    })
    @PostMapping(value = "/saveAuthRole")
    public ResponseData<Boolean> saveAuthRole(@RequestBody UserInfoRoleDTO userInfoRoleDto) {
        return ResponseData.success(userInfoService.saveAuthRole(userInfoRoleDto));
    }

}
