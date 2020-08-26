package com.dcy.service.apiadmin.api;

import com.dcy.db.base.service.BaseService;
import com.dcy.service.apiadmin.model.Dict;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author dcy
 * @since 2020-08-26
 */
public interface DictService extends BaseService<Dict> {

    /**
     * 根据分组类型查询字典项tree
     *
     * @param groupType
     * @return
     */
    List<Dict> getDictTreeListByGroupType(String groupType);

    /**
     * 获取tree-table 数据
     *
     * @return
     */
    List<Dict> getDictTreeTableList();

    /**
     * 根据类型查询字典项
     * @param type
     * @return
     */
    List<Dict> getDictListByType(String type);

}
