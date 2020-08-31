package com.dcy.admin.api.api;

import com.dcy.admin.api.dto.RoleResourceDto;
import com.dcy.admin.api.model.Resources;
import com.dcy.admin.api.model.Role;
import com.dcy.db.base.service.BaseService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
public interface RoleService extends BaseService<Role> {

    /**
     *根据角色id查询已授权的权限列表
     * @param roleId
     * @return
     */
    List<Resources> getAuthResourceListByRoleId(String roleId);

    /**
     * 保存授权权限
     * @param roleResourceDto
     * @return
     */
    Boolean saveAuthResource(RoleResourceDto roleResourceDto);
}
