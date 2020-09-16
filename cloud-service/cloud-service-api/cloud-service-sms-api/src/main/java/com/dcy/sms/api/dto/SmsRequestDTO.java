package com.dcy.sms.api.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：dcy
 * @Description: 消息对象
 * @Date: 2020/9/16 8:37
 */
@Data
@Builder
public class SmsRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     * 单发：15988888888
     * 群发：["15000000000","15000000001"]
     */
    private String phone;
    /**
     * 阿里 sms 模板 code
     */
    private String templateCode;
    /**
     * 签名
     * 单发：云通信
     * 群发：["云通信","云通信"]
     */
    private String signName;
    /**
     * json格式体
     * { "code": "123456" }
     */
    private String param;

}
