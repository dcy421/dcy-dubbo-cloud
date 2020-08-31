package com.dcy.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author：dcy
 * @Description: 授权权限使用
 * @Date: 2020/8/26 9:44
 */
@Data
@ApiModel(value="授权权限对象", description="授权权限使用")
public class RoleResourceDto {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色Id")
    private String roleId;

    @ApiModelProperty(value = "授权权限Ids")
    private List<String> resIds;
}
