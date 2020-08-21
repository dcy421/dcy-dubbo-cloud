package com.dcy.service.bizadmin.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：dcy
 * @Description: fastJson 配置
 * @Date: 2019/9/6 14:51
 */
@Configuration
public class FJsonConfig {

    @Bean
    public HttpMessageConverter configureMessageConverters() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                SerializerFeature.PrettyFormat,// @JSONField 转换使用
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,//——–是否输出值为null的字段,默认为false
//                SerializerFeature.WriteNullStringAsEmpty,//—字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullListAsEmpty//—–List字段如果为null,输出为[],而非null
//                SerializerFeature.WriteNullNumberAsZero//—-数值字段如果为null,输出为0,而非null
        );
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        config.setDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        List<MediaType> mediaTypeList = new ArrayList<>();
        // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
        mediaTypeList.add(MediaType.ALL);
        converter.setSupportedMediaTypes(mediaTypeList);
        return converter;
    }
}
