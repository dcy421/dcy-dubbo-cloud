package com.dcy.admin.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dcy.admin.api.model.Resources;
import com.dcy.admin.api.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     *根据角色id查询已授权的权限列表
     * @param roleId
     * @return
     */
    List<Resources> getAuthResourceListByRoleId(@Param("roleId") String roleId);
}
