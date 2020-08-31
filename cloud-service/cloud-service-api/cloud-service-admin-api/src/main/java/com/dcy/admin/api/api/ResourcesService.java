package com.dcy.admin.api.api;

import com.dcy.admin.api.model.Resources;
import com.dcy.db.base.service.BaseService;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
public interface ResourcesService extends BaseService<Resources> {

    /**
     * 获取tree-table 数据
     * @return
     */
    List<Resources> getResourceTreeTableList();

    /**
     * 获取tree 数据
     * @param roleId
     * @return
     */
    List<String> getResourceTreeListByRoleId(String roleId);
}
