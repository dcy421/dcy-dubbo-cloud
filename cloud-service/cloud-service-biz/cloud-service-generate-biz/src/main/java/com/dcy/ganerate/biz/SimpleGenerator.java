package com.dcy.ganerate.biz;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.dcy.db.base.model.BaseModel;
import com.dcy.ganerate.biz.model.GenerModel;
import com.dcy.ganerate.biz.utils.GenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：dcy
 * @Description: 基础版代码生成器
 * @Date: 2020/7/22 10:25
 */
public class SimpleGenerator {
    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        GenerModel generModel = GenUtils.getGenerByProps();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //开启 BaseResultMap
        gc.setBaseResultMap(true);
        //开启 baseColumnList
        gc.setBaseColumnList(true);
        gc.setSwagger2(true);
        gc.setFileOverride(true);

        //gc.setOutputDir(projectPath + "/mybatis-plus-sample-generator/src/main/java");
        // 生成文件的输出目录
        gc.setOutputDir(generModel.getPack());
        //开发人员
        gc.setAuthor("dcy");
        // 是否打开输出目录
        gc.setOpen(false);
        // 修改service名称
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(generModel.getDbUrl());
        dsc.setDriverName(generModel.getDriverName());
        dsc.setUsername(generModel.getUsername());
        dsc.setPassword(generModel.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dcy");
        pc.setController("biz.controller");
        pc.setEntity("api.model");
        pc.setService("api.api");
        pc.setServiceImpl("provider.service");
        pc.setMapper("provider.mapper");
        pc.setXml("mapper");
        String models = generModel.getModules();
//        父包模块名
        pc.setModuleName(models);
        mpg.setPackageInfo(pc);

        setTemplateMapper(mpg, generModel);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        // 设置父类
//        strategy.setSuperControllerClass("com.dcy.db.base.controller.BaseController");
        strategy.setSuperEntityClass(BaseModel.class);
        strategy.setSuperServiceClass("com.dcy.db.base.service.BaseService");
        strategy.setSuperServiceImplClass("com.dcy.db.base.service.impl.BaseServiceImpl");

        strategy.setInclude(generModel.getTableName().split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityColumnConstant(true);
        strategy.setRestControllerStyle(true);
        strategy.setTablePrefix(generModel.getPrefix().split(","));
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/simple/controller.java");
        tc.setService("/simple/service.java");
        tc.setServiceImpl("/simple/serviceImpl.java");
        tc.setEntity("/simple/entity.java");
        tc.setMapper("/simple/mapper.java");
        tc.setXml("/simple/mapper.xml");
        mpg.setTemplate(tc);
        mpg.execute();
    }

    private static void setTemplateMapper(AutoGenerator mpg, final GenerModel generModel) {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("modulesName", generModel.getModulesName());
                map.put("modulesApi", generModel.getModules());
                map.put("modules", generModel.getModulesUp());
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/vue/manage-element.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return generModel.getPack() + "//vue//" + generModel.getModules() + "//" + generModel.getModules() + "-manage.vue";
            }
        });
        focList.add(new FileOutConfig("/vue/vue.js.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return generModel.getPack() + "//vue//" + generModel.getModules() + "//" + generModel.getModules() + ".js";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

}
