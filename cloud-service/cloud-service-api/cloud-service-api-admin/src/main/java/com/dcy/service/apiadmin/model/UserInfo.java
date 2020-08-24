package com.dcy.service.apiadmin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dcy.db.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_info")
@ApiModel(value="UserInfo对象", description="用户表")
public class UserInfo extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户类型（0、管理员；1、普通用户）")
    private String userType;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "性别（0、男；1、女）")
    private String sex;

    @ApiModelProperty(value = "头像")
    private String avatarPath;

    @ApiModelProperty(value = "帐号状态（0、正常；1、禁用）")
    private String userStatus;

    @ApiModelProperty(value = "资源信息")
    @TableField(exist = false)
    private Set<String> resources;

    public static final String USER_ID = "user_id";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String NICK_NAME = "nick_name";

    public static final String USER_TYPE = "user_type";

    public static final String EMAIL = "email";

    public static final String PHONE_NUMBER = "phone_number";

    public static final String SEX = "sex";

    public static final String AVATAR_PATH = "avatar_path";

    public static final String USER_STATUS = "user_status";

}
