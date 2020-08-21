package com.dcy.service.provideradmin.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcy.common.utils.BPwdEncoderUtil;
import com.dcy.common.utils.JwtUtil;
import com.dcy.service.apiadmin.api.UserInfoService;
import com.dcy.service.apiadmin.model.UserInfo;
import com.dcy.service.provideradmin.mapper.UserInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
@DubboService(version = "1.0.0")
@Service
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public String login(UserInfo userInfo) {
        UserInfo userInfo1 = super.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUsername, userInfo.getUsername()));
        if (BPwdEncoderUtil.matches(userInfo.getPassword(), userInfo1.getPassword().replace("{bcrypt}", ""))) {
            return JwtUtil.generateToken(JSON.toJSONString(userInfo1));
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo(String token) {
        String validateToken = JwtUtil.validateToken(token);
        if (StrUtil.isNotBlank(validateToken)) {
            UserInfo userInfo = JSON.parseObject(validateToken, UserInfo.class);
            userInfo.setPassword(null);
            return userInfo;
        }
        return null;
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        return super.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUsername, username));
    }

    @Override
    public List<Map<String, Object>> getResourcesByUserId(String userId) {
        return baseMapper.getResourcesByUserId(userId);
    }
}
