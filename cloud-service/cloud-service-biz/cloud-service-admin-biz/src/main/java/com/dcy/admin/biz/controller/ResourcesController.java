package com.dcy.admin.biz.controller;

import com.dcy.admin.api.api.ResourcesService;
import com.dcy.admin.api.model.Resources;
import com.dcy.common.model.ResponseData;
import com.dcy.db.base.controller.BaseController;
import com.dcy.db.base.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/8/26 9:31
 */
@RestController
@RequestMapping("/resources")
@Api(value = "ResourcesController", tags = {"资源操作接口"})
public class ResourcesController extends BaseController<Resources> {

    @DubboReference(version = "1.0.0")
    private ResourcesService resourcesService;

    @Override
    protected <T extends BaseService<Resources>> T getService() {
        return (T) resourcesService;
    }

    @ApiOperation(value = "获取tree-table列表数据", notes = "获取tree-table列表数据")
    @GetMapping(value = "/getResourceTreeTableList")
    public ResponseData<List<Resources>> getModuleTreeTableList() {
        return ResponseData.success(resourcesService.getResourceTreeTableList());
    }

    @ApiOperation(value = "获取tree列表数据", notes = "获取tree列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", dataType = "roleId", paramType = "query")
    })
    @GetMapping(value = "/getResourceTreeListByRoleId")
    public ResponseData<List<String>> getResourceTreeListByRoleId(String roleId) {
        return ResponseData.success(resourcesService.getResourceTreeListByRoleId(roleId));
    }

}
