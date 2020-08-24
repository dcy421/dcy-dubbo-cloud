package com.dcy.service.apiadmin.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dcy.db.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author dcy
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_resources")
@ApiModel(value="Resources对象", description="资源表")
public class Resources extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "父级id")
    private String parentId;

    @ApiModelProperty(value = "父级ids")
    private String parentIds;

    @ApiModelProperty(value = "资源名称")
    private String resName;

    @ApiModelProperty(value = "资源code")
    private String resCode;

    @ApiModelProperty(value = "资源path")
    private String resPath;

    @ApiModelProperty(value = "请求方式")
    private String httpMethod;

    @ApiModelProperty(value = "状态（0、正常；1、禁用）")
    private Boolean resStatus;

    @ApiModelProperty(value = "类型（0、模块；1、链接）")
    private Boolean resType;

    @ApiModelProperty(value = "排序")
    private BigDecimal resSort;

    public static final String ID = "id";

    public static final String PARENT_ID = "parent_id";

    public static final String PARENT_IDS = "parent_ids";

    public static final String RES_NAME = "res_name";

    public static final String RES_CODE = "res_code";

    public static final String RES_PATH = "res_path";

    public static final String HTTP_METHOD = "http_method";

    public static final String RES_STATUS = "res_status";

    public static final String RES_TYPE = "res_type";

    public static final String RES_SORT = "res_sort";

}
