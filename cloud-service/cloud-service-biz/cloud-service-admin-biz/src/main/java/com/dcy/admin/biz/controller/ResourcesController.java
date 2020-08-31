package com.dcy.admin.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dcy.common.model.ResponseData;
import com.dcy.admin.api.api.ResourcesService;
import com.dcy.admin.api.model.Resources;
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
 * @Date: 2020/8/26 9:31
 */
@RestController
@RequestMapping("/resources")
@Api(value = "ResourcesController", tags = {"资源操作接口"})
public class ResourcesController {

    @DubboReference(version = "1.0.0")
    private ResourcesService resourcesService;

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

    @ApiOperation(value = "添加", notes = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Resources", name = "resources", value = "Resources对象", required = true)
    })
    @PostMapping(value = "/save")
    public ResponseData<Boolean> save(@RequestBody Resources resources) {
        return ResponseData.success(resourcesService.save(resources));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Resources", name = "resources", value = "Resources对象", required = true)
    })
    @PostMapping(value = "/update")
    public ResponseData<Boolean> update(@RequestBody Resources resources) {
        return ResponseData.success(resourcesService.updateById(resources));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping(value = "/delete")
    public ResponseData<Boolean> delete(@RequestParam String id) {
        return ResponseData.success(resourcesService.removeById(id));
    }

}
