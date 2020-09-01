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
        //开发人员
        gc.setAuthor("dcy")
            //开启 BaseResultMap
            .setBaseResultMap(true)
            //开启 baseColumnList
            .setBaseColumnList(true)
            // 开启swagger
            .setSwagger2(true)
            .setFileOverride(true)
            // 生成文件的输出目录
            .setOutputDir(generModel.getPack())
            // 是否打开输出目录
            .setOpen(false)
            // 修改service名称
            .setServiceName("%sService");

        //gc.setOutputDir(projectPath + "/mybatis-plus-sample-generator/src/main/java");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(generModel.getDbUrl())
            .setDriverName(generModel.getDriverName())
            .setUsername(generModel.getUsername())
            .setPassword(generModel.getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        String models = generModel.getModules();

        pc.setParent("com.dcy")
            .setController("biz.controller")
            .setEntity("api.model")
            .setService("api.api")
            .setServiceImpl("provider.service")
            .setMapper("provider.mapper")
            .setXml("mapper")
            //父包模块名
            .setModuleName(models);
        mpg.setPackageInfo(pc);

        // 自定义生成文件
        setTemplateMapper(mpg, generModel);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel)
            .setColumnNaming(NamingStrategy.underline_to_camel)
            .setEntityLombokModel(true)
            // 设置父类
    //        strategy.setSuperControllerClass("com.dcy.db.base.controller.BaseController");
            .setSuperEntityClass(BaseModel.class)
            .setSuperServiceClass("com.dcy.db.base.service.BaseService")
            .setSuperServiceImplClass("com.dcy.db.base.service.impl.BaseServiceImpl")

            .setInclude(generModel.getTableName().split(","))
            .setControllerMappingHyphenStyle(true)
            .setEntityColumnConstant(true)
            .setRestControllerStyle(true)
            .setTablePrefix(generModel.getPrefix().split(","));
        mpg.setStrategy(strategy);


        // 模板引擎 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 模板路径配置
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/simple/controller.java")
            .setService("/simple/service.java")
            .setServiceImpl("/simple/serviceImpl.java")
            .setEntity("/simple/entity.java")
            .setMapper("/simple/mapper.java")
            .setXml("/simple/mapper.xml");
        mpg.setTemplate(tc);

        // 生成代码
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
