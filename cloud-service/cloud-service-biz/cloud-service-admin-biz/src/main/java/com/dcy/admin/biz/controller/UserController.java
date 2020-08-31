package com.dcy.admin.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dcy.common.model.ResponseData;
import com.dcy.admin.api.api.UserInfoService;
import com.dcy.admin.api.dto.UserInfoRoleDto;
import com.dcy.admin.api.model.Role;
import com.dcy.admin.api.model.UserInfo;
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
public class UserController {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @DubboReference(version = "1.0.0")
    private UserInfoService userInfoService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userInfo", value = "UserInfo对象", dataType = "UserInfo", paramType = "query")
    })
    @GetMapping(value = "/page")
    public ResponseData<IPage<UserInfo>> page(UserInfo userInfo) {
        return ResponseData.success(userInfoService.pageList(userInfo));
    }

    @ApiOperation(value = "根据用户名获取用户信息", notes = "根据用户名获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/getUserInfoByUsername")
    public ResponseData<UserInfo> getUserInfoByUsername(@RequestParam String username) {
        return ResponseData.success(userInfoService.getUserInfoByUsername(username));
    }

    @ApiOperation(value = "添加", notes = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "UserInfo", name = "userInfo", value = "UserInfo对象", required = true)
    })
    @PostMapping(value = "/save")
    public ResponseData<Boolean> save(@RequestBody UserInfo userInfo) {
        userInfo.setPassword("{bcrypt}" + passwordEncoder.encode(userInfo.getPassword()));
        return ResponseData.success(userInfoService.save(userInfo));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "UserInfo", name = "userInfo", value = "UserInfo对象", required = true)
    })
    @PostMapping(value = "/update")
    public ResponseData<Boolean> update(@RequestBody UserInfo userInfo) {
        return ResponseData.success(userInfoService.updateById(userInfo));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping(value = "/delete")
    public ResponseData<Boolean> delete(@RequestParam String id) {
        return ResponseData.success(userInfoService.removeById(id));
    }

    @ApiOperation(value = "根据list删除", notes = "根据list删除")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Boolean> deleteBatch(@RequestBody List<String> idList) {
        return ResponseData.success(userInfoService.removeByIds(idList));
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
    public ResponseData<Boolean> saveAuthRole(@RequestBody UserInfoRoleDto userInfoRoleDto) {
        return ResponseData.success(userInfoService.saveAuthRole(userInfoRoleDto));
    }
}
