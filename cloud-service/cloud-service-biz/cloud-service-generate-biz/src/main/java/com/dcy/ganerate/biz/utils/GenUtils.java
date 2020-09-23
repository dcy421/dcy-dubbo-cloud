package com.dcy.ganerate.biz.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.dialect.Props;
import com.dcy.ganerate.biz.model.GenerModel;

public class GenUtils {
    private static final Props props;

    static {
        props = new Props("db.properties", CharsetUtil.UTF_8);
    }

    public static GenerModel getGenerByProps() {
        GenerModel generModel = new GenerModel();
        generModel.setPack(props.getStr("db.pack"));
        generModel.setDbUrl(props.getStr("db.url"));
        generModel.setDriverName(props.getStr("db.driverName"));
        generModel.setUsername(props.getStr("db.username"));
        generModel.setPassword(props.getStr("db.password"));
        generModel.setTableName(props.getStr("table.tableName"));
        generModel.setModules(props.getStr("table.modules"));
        generModel.setPrefix(props.getStr("table.prefix"));
        return generModel;
    }
}
