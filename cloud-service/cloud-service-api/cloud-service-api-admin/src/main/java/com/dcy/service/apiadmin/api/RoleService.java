package com.dcy.service.apiadmin.api;

import com.dcy.db.base.service.BaseService;
import com.dcy.service.apiadmin.dto.RoleResourceDto;
import com.dcy.service.apiadmin.model.Resources;
import com.dcy.service.apiadmin.model.Role;

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
