package com.dcy.service.provideradmin.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dcy.common.constant.Constant;
import com.dcy.db.base.service.impl.BaseServiceImpl;
import com.dcy.service.apiadmin.api.DictService;
import com.dcy.service.apiadmin.model.Dict;
import com.dcy.service.provideradmin.mapper.DictMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 字典类型表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-08-26
 */
@DubboService(version = "1.0.0")
@Service
@Transactional
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements DictService {

    @Cacheable(value = "dict", key = "'group-'+#groupType", unless = "#result eq null")
    @Override
    public List<Dict> getDictTreeListByGroupType(String groupType) {
        // 获取所有字典项
        List<Dict> dictList = baseMapper.selectList(new LambdaQueryWrapper<Dict>().eq(Dict::getDictType, groupType).orderByAsc(Dict::getDictSort));
        List<Dict> treeDataList = new ArrayList<>();
        dictList.stream().forEach(sysDict -> {
            if (Constant.DEFAULT_PARENT_VAL.equalsIgnoreCase(sysDict.getParentId())) {
                treeDataList.add(sysDict);
            }
        });
        recursionTreeTableChildren(treeDataList, dictList);
        return treeDataList;
    }


    @Override
    public List<Dict> getDictTreeTableList() {
        // 获取所有字典项
        List<Dict> dictList = baseMapper.selectList(new LambdaQueryWrapper<Dict>().orderByAsc(Dict::getDictSort));
        List<Dict> treeDataList = new ArrayList<>();
        dictList.stream().forEach(sysDict -> {
            if (Constant.DEFAULT_PARENT_VAL.equalsIgnoreCase(sysDict.getParentId())) {
                treeDataList.add(sysDict);
            }
        });
        recursionTreeTableChildren(treeDataList, dictList);
        return treeDataList;
    }

    @Cacheable(value = "dict", key = "#type", unless = "#result eq null")
    @Override
    public List<Dict> getDictListByType(String type) {
        return super.list(new LambdaQueryWrapper<Dict>().eq(Dict::getType, type).orderByAsc(Dict::getDictSort));
    }

    private void recursionTreeTableChildren(List<Dict> treeDataList, List<Dict> dictList) {
        for (Dict treeData : treeDataList) {
            List<Dict> childrenList = new ArrayList<>();
            for (Dict sysDict : dictList) {
                if (sysDict.getParentId().equals(treeData.getId())) {
                    childrenList.add(sysDict);
                }
            }
            if (!CollUtil.isEmpty(childrenList)) {
                treeData.setChildren(childrenList);
                recursionTreeTableChildren(childrenList, dictList);
            }
        }
    }

    @CacheEvict(value = "dict", allEntries = true)
    @Override
    public boolean save(Dict entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "dict", allEntries = true)
    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @CacheEvict(value = "dict", allEntries = true)
    @Override
    public boolean updateById(Dict entity) {
        return super.updateById(entity);
    }

    @CacheEvict(value = "dict", allEntries = true)
    @Override
    public boolean saveOrUpdate(Dict entity) {
        return super.saveOrUpdate(entity);
    }

    @CacheEvict(value = "dict", allEntries = true)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }
}
