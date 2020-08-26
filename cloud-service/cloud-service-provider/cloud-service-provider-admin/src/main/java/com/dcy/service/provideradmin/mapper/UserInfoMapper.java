package com.dcy.service.provideradmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dcy.service.apiadmin.model.Role;
import com.dcy.service.apiadmin.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据userId 查询权限
     * @param userId
     * @return
     */
    List<Map<String, Object>> getResourcesByUserId(@Param("userId") String userId);

    /**
     *根据用户id 查询已授权角色列表
     * @param userId
     * @return
     */
    List<Role> getAuthRoleListByUserId(@Param("userId") String userId);

}
