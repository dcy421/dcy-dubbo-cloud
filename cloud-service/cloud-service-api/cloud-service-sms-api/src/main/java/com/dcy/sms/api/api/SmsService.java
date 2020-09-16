package com.dcy.sms.api.api;

import com.dcy.sms.api.dto.SmsRequestDTO;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/9/16 8:09
 */
public interface SmsService {

    /**
     * 发送短信
     * @param smsRequestDTO
     * @return
     */
    Boolean sendSms(SmsRequestDTO smsRequestDTO);

    /**
     * 群发短信
     * @param smsRequestDTO
     * @return
     */
    Boolean sendBatchSms(SmsRequestDTO smsRequestDTO);
}
