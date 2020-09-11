package com.dcy.admin.biz.controller;

import com.dcy.admin.api.api.RoleService;
import com.dcy.admin.api.dto.RoleResourceDto;
import com.dcy.admin.api.model.Resources;
import com.dcy.admin.api.model.Role;
import com.dcy.common.model.ResponseData;
import com.dcy.db.base.controller.BaseController;
import com.dcy.db.base.service.BaseService;
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
public class RoleController extends BaseController<Role> {

    @DubboReference(version = "1.0.0")
    private RoleService roleService;

    @Override
    protected <T extends BaseService<Role>> T getService() {
        return (T) roleService;
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
