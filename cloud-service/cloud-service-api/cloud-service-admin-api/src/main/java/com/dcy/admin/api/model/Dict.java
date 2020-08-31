package com.dcy.admin.api.model;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.dcy.db.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author dcy
 * @since 2020-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict")
@ApiModel(value="Dict对象", description="字典类型表")
public class Dict extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典主键")
    private String id;

    @ApiModelProperty(value = "父级id")
    private String parentId;

    @ApiModelProperty(value = "父级ids")
    private String parentIds;

    @ApiModelProperty(value = "字典类型")
    private String dictType;

    @ApiModelProperty(value = "字典名称")
    private String dictLabel;

    @ApiModelProperty(value = "字典键值")
    private String dictValue;

    @ApiModelProperty(value = "排序")
    private BigDecimal dictSort;

    @ApiModelProperty(value = "子类型")
    private String type;

    /**
     * 子数据
     */
    @TableField(exist = false)
    private List<Dict> children;

    public static final String DICT_ID = "dict_id";

    public static final String PARENT_ID = "parent_id";

    public static final String PARENT_IDS = "parent_ids";

    public static final String DICT_TYPE = "dict_type";

    public static final String DICT_LABLE = "dict_lable";

    public static final String DICT_VALUE = "dict_value";

    public static final String DICT_SORT = "dict_sort";

    public static final String TYPE = "type";

}
