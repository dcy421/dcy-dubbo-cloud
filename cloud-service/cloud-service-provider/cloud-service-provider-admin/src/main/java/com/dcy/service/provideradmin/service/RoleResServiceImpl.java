package com.dcy.service.provideradmin.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcy.service.apiadmin.model.RoleRes;
import com.dcy.service.provideradmin.mapper.RoleResMapper;
import com.dcy.service.apiadmin.api.RoleResService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色和资源关联表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
@DubboService(version = "1.0.0")
@Service
@Transactional
public class RoleResServiceImpl extends ServiceImpl<RoleResMapper, RoleRes> implements RoleResService {

}
