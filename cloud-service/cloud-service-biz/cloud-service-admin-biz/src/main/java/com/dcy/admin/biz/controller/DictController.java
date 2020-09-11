package com.dcy.admin.biz.controller;

import com.dcy.admin.api.api.DictService;
import com.dcy.admin.api.model.Dict;
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
 * @Date: 2020/8/26 10:32
 */
@RestController
@RequestMapping("/dict")
@Api(value = "DictController", tags = {"字典操作接口"})
public class DictController extends BaseController<Dict> {

    @DubboReference(version = "1.0.0")
    private DictService dictService;

    @Override
    protected <T extends BaseService<Dict>> T getService() {
        return (T) dictService;
    }

    @ApiOperation(value = "获取tree-table列表数据", notes = "获取tree-table列表数据")
    @GetMapping(value = "/getDictTreeTableList")
    public ResponseData<List<Dict>> getDictTreeTableList() {
        return ResponseData.success(dictService.getDictTreeTableList());
    }

    @ApiOperation(value = "根据类型查询字典项", notes = "根据类型查询字典项（type字段）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "type字段", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/getDictListByType")
    public ResponseData<List<Dict>> getDictListByType(String type) {
        return ResponseData.success(dictService.getDictListByType(type));
    }

    @ApiOperation(value = "根据分组类型查询字典项tree", notes = "根据分组类型查询字典项tree（dict_type字段）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupType", value = "dict_type字段", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping(value = "/getDictTreeListByGroupType")
    public ResponseData<List<Dict>> getDictTreeListByGroupType(String groupType) {
        return ResponseData.success(dictService.getDictTreeListByGroupType(groupType));
    }
}
