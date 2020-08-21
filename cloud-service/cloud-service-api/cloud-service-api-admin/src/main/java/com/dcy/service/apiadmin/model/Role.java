package com.dcy.service.apiadmin.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.dcy.basedb.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限字符串
     */
    private String roleKey;

    /**
     * 角色状态（0、正常；1、禁用）
     */
    private String roleStatus;

    public static final String ROLE_ID = "role_id";

    public static final String ROLE_NAME = "role_name";

    public static final String ROLE_KEY = "role_key";

    public static final String ROLE_STATUS = "role_status";

}
