package com.dcy.ganerate.biz.dto;

import com.baomidou.mybatisplus.generator.config.*;
import lombok.Data;

/**
 * @Author：dcy
 * @Description: 暂时弃用
 * @Date: 2020/9/23 8:36
 */
@Data
public class GeneratorDTO {

    /**
     * 全局配置
     */
    private DataSourceConfig dataSourceConfig;
    /**
     * 数据库配置
     */
    private GlobalConfig globalConfig;
    /**
     * 跟包相关的配置项
     */
    private PackageConfig packageConfig;
    /**
     * 策略配置项
     */
    private StrategyConfig strategyConfig;
    /**
     * 模板路径配置项
     */
    private TemplateConfig templateConfig;
    /**
     * 是否生成vue对应文件
     */
    private Boolean vueFlag=Boolean.TRUE;
    private String modulesName;
}
