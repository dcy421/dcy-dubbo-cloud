package com.dcy.ganerate.biz.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.dcy.ganerate.biz.dto.GeneratorDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：dcy
 * @Description: 暂时弃用
 * @Date: 2020/9/23 8:44
 */
public class GeneratorUtil {

    /**
     * 生成代码
     * @param generatorDTO
     */
    public static void generatorCode(GeneratorDTO generatorDTO) {
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(generatorDTO.getGlobalConfig());
        mpg.setDataSource(generatorDTO.getDataSourceConfig());
        mpg.setPackageInfo(generatorDTO.getPackageConfig());

        // 设置策略
        mpg.setStrategy(generatorDTO.getStrategyConfig());

        // 设置模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 设置模板路径
        mpg.setTemplate(generatorDTO.getTemplateConfig());

        // 自定义生成文件
        setTemplateMapper(mpg, generatorDTO);

        // 生成代码
        mpg.execute();
    }


    private static void setTemplateMapper(AutoGenerator mpg, final GeneratorDTO generatorDTO) {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】  ${cfg.abc}
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("modulesName", generatorDTO.getModulesName());
                map.put("modulesApi", generatorDTO.getPackageConfig().getModuleName());
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        focList.add(new FileOutConfig("/vue/manage-element.vue.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return generatorDTO.getGlobalConfig().getOutputDir() + "//vue//" + generatorDTO.getPackageConfig().getModuleName() + "//" + generatorDTO.getPackageConfig().getModuleName() + "-manage.vue";
            }
        });
        focList.add(new FileOutConfig("/vue/vue.js.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return generatorDTO.getGlobalConfig().getOutputDir() + "//vue//" + generatorDTO.getPackageConfig().getModuleName() + "//" + generatorDTO.getPackageConfig().getModuleName() + ".js";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }

}
