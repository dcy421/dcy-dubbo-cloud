package com.dcy.admin.biz.controller;

import com.dcy.common.model.ResponseData;
import com.dcy.admin.api.api.DictService;
import com.dcy.admin.api.model.Dict;
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
 * @Date: 2020/8/26 10:32
 */
@RestController
@RequestMapping("/dict")
@Api(value = "DictController", tags = {"字典操作接口"})
public class DictController {


    @DubboReference(version = "1.0.0")
    private DictService dictService;

    @ApiOperation(value = "添加", notes = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Dict", name = "dict", value = "Dict对象", required = true)
    })
    @PostMapping(value = "/save")
    public ResponseData<Boolean> save(@RequestBody Dict dict) {
        return ResponseData.success(dictService.save(dict));
    }

    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "Dict", name = "dict", value = "Dict对象", required = true)
    })
    @PostMapping(value = "/update")
    public ResponseData<Boolean> update(@RequestBody Dict dict) {
        return ResponseData.success(dictService.updateById(dict));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping(value = "/delete")
    public ResponseData<Boolean> delete(@RequestParam String id) {
        return ResponseData.success(dictService.removeById(id));
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
