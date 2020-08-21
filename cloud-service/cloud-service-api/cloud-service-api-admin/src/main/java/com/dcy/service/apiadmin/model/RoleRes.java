package com.dcy.service.apiadmin.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * <p>
 * 角色和资源关联表
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_res")
public class RoleRes implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 资源id
     */
    private String resId;


    public static final String ROLE_ID = "role_id";

    public static final String RES_ID = "res_id";

}
