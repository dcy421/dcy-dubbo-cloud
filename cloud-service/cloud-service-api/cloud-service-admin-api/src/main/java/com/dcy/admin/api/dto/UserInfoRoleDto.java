package com.dcy.admin.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author：dcy
 * @Description: 授权角色使用
 * @Date: 2020/8/26 9:19
 */
@Data
@ApiModel(value="授权角色对象", description="授权角色使用")
public class UserInfoRoleDto {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "授权角色Ids")
    private List<String> roleIds;
}
