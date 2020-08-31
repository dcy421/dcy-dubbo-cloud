package com.dcy.admin.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dcy.common.model.ResponseData;
import com.dcy.admin.api.api.RoleService;
import com.dcy.admin.api.dto.RoleResourceDto;
import com.dcy.admin.api.model.Resources;
import com.dcy.admin.api.model.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/8/26 9:29
 */
@RestController
@RequestMapping("/role")
@Api(value = "RoleController", tags = {"角色操作接口"})
public class RoleController {

    @DubboReference(version = "1.0.0")
    private RoleService roleService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "Role对象", dataType = "Role", paramType = "query")
    })
    @GetMapping(value = "/page")
    public ResponseData<IPage<Role>> page(Role role) {
        return ResponseData.success(roleService.pageList(role));
    }

    @ApiOperation(value = "获取全部信息", notes = "获取全部信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "Role对象", dataType = "Role", paramType = "query")
    })
    @GetMapping(value = "/all")
    public ResponseData<List<Role>> all() {
        return ResponseData.success(roleService.list());
    }

    @ApiOperation(value = "添加", notes = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Role", name = "role", value = "Role对象", required = true)
    })
    @PostMapping(value = "/save")
    public ResponseData<Boolean> save(@RequestBody Role role) {
        return ResponseData.success(roleService.save(role));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Role", name = "role", value = "Role对象", required = true)
    })
    @PostMapping(value = "/update")
    public ResponseData<Boolean> update(@RequestBody Role role) {
        return ResponseData.success(roleService.updateById(role));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping(value = "/delete")
    public ResponseData<Boolean> delete(@RequestParam String id) {
        return ResponseData.success(roleService.removeById(id));
    }

    @ApiOperation(value = "根据list删除", notes = "根据list删除")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Boolean> deleteBatch(@RequestBody List<String> idList) {
        return ResponseData.success(roleService.removeByIds(idList));
    }

    @ApiOperation(value = "获取已授权的权限列表", notes = "根据角色id查询已授权的权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/getAuthResourceListByRoleId")
    public ResponseData<List<Resources>> getAuthResourceListByRoleId(String roleId) {
        return ResponseData.success(roleService.getAuthResourceListByRoleId(roleId));
    }

    @ApiOperation(value = "保存授权权限", notes = "保存授权权限")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "RoleResourceDto", name = "roleResourceDto", value = "对象参数", required = true)
    })
    @PostMapping(value = "/saveAuthResource")
    public ResponseData<Boolean> saveAuthResource(@RequestBody RoleResourceDto roleResourceDto) {
        return ResponseData.success(roleService.saveAuthResource(roleResourceDto));
    }
}
