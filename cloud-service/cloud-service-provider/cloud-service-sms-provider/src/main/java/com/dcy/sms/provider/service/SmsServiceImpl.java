package com.dcy.sms.provider.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.dcy.sms.api.api.SmsService;
import com.dcy.sms.api.dto.SmsRequestDTO;
import com.dcy.sms.provider.properties.SmsProperties;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/9/16 8:16
 */
@DubboService(version = "1.0.0",timeout = 3000)
@Service
public class SmsServiceImpl implements SmsService {

    private static final String ALIYUN_PRODUCT_DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String ALIYUN_SYS_ACTION = "SendSms";
    private static final String ALIYUN_SYS_BATCH_ACTION = "SendBatchSms";
    private static final String SUCCESS_RESULT = "OK";

    @Autowired
    private IAcsClient iAcsClient;
    @Autowired
    private SmsProperties smsProperties;

    @Override
    public Boolean sendSms(SmsRequestDTO smsRequestDTO) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(ALIYUN_PRODUCT_DOMAIN);
        request.setSysVersion(smsProperties.getVersion());
        request.setSysAction(ALIYUN_SYS_ACTION);
        if (StrUtil.isNotBlank(smsProperties.getRegionId())){
            request.putQueryParameter("RegionId", smsProperties.getRegionId());
        }
        if (StrUtil.isNotBlank(smsRequestDTO.getPhone())){
            request.putQueryParameter("PhoneNumbers", smsRequestDTO.getPhone());
        }
        if (StrUtil.isNotBlank(smsRequestDTO.getSignName())){
            request.putQueryParameter("SignName", smsRequestDTO.getSignName());
        }
        if (StrUtil.isNotBlank(smsRequestDTO.getTemplateCode())){
            request.putQueryParameter("TemplateCode", smsRequestDTO.getTemplateCode());
        }
        if (StrUtil.isNotBlank(smsRequestDTO.getParam())){
            request.putQueryParameter("TemplateParam", smsRequestDTO.getParam());
        }
        try {
            CommonResponse response = iAcsClient.getCommonResponse(request);
            JSONObject jsonObject = JSON.parseObject(response.getData());
            return SUCCESS_RESULT.equals(jsonObject.getString("Code"));
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean sendBatchSms(SmsRequestDTO smsRequestDTO) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(ALIYUN_PRODUCT_DOMAIN);
        request.setSysVersion(smsProperties.getVersion());
        request.setSysAction(ALIYUN_SYS_BATCH_ACTION);
        if (StrUtil.isNotBlank(smsProperties.getRegionId())){
            request.putQueryParameter("RegionId", smsProperties.getRegionId());
        }
        if (StrUtil.isNotBlank(smsRequestDTO.getPhone())){
            request.putQueryParameter("PhoneNumberJson", smsRequestDTO.getPhone());
        }
        if (StrUtil.isNotBlank(smsRequestDTO.getSignName())){
            request.putQueryParameter("SignNameJson", smsRequestDTO.getSignName());
        }
        if (StrUtil.isNotBlank(smsRequestDTO.getTemplateCode())){
            request.putQueryParameter("TemplateCode", smsRequestDTO.getTemplateCode());
        }
        if (StrUtil.isNotBlank(smsRequestDTO.getParam())){
            request.putQueryParameter("TemplateParamJson", smsRequestDTO.getParam());
        }
        try {
            CommonResponse response = iAcsClient.getCommonResponse(request);
            JSONObject jsonObject = JSON.parseObject(response.getData());
            return SUCCESS_RESULT.equals(jsonObject.getString("Code"));
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
