package com.dcy.service.provideradmin.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dcy.common.constant.Constant;
import com.dcy.db.base.service.impl.BaseServiceImpl;
import com.dcy.service.apiadmin.api.ResourcesService;
import com.dcy.service.apiadmin.model.Resources;
import com.dcy.service.apiadmin.model.RoleRes;
import com.dcy.service.provideradmin.mapper.ResourcesMapper;
import com.dcy.service.provideradmin.mapper.RoleResMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
@DubboService(version = "1.0.0")
@Service
@Transactional
public class ResourcesServiceImpl extends BaseServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

    @Autowired
    private RoleResMapper roleResMapper;

    @Override
    public List<Resources> getResourceTreeTableList() {
        List<Resources> sysModuleResources = baseMapper.selectList(new LambdaQueryWrapper<Resources>().orderByAsc(Resources::getResSort));
        List<Resources> treeDataList = new ArrayList<>();
        sysModuleResources.stream().forEach(sysModuleResources1 -> {
            if (Constant.DEFAULT_PARENT_VAL.equalsIgnoreCase(sysModuleResources1.getParentId())) {
                treeDataList.add(sysModuleResources1);
            }
        });
        recursionTreeTableChildren(treeDataList, sysModuleResources);
        return treeDataList;
    }

    @Override
    public List<String> getResourceTreeListByRoleId(String roleId) {
        return roleResMapper.selectList(new LambdaQueryWrapper<RoleRes>().eq(RoleRes::getRoleId, roleId))
                .stream().map(RoleRes::getResId)
                .collect(Collectors.toList());
    }

    private void recursionTreeTableChildren(List<Resources> treeDataList, List<Resources> sysModuleResources) {
        for (Resources treeData : treeDataList) {
            List<Resources> childrenList = new ArrayList<>();
            for (Resources sysModuleResources1 : sysModuleResources) {
                if (sysModuleResources1.getParentId().equals(treeData.getId())) {
                    childrenList.add(sysModuleResources1);
                }
            }
            if (!CollUtil.isEmpty(childrenList)) {
                treeData.setChildren(childrenList);
                recursionTreeTableChildren(childrenList, sysModuleResources);
            }
        }
    }
}
