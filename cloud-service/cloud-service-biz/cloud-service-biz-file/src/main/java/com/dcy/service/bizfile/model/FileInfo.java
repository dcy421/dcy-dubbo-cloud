package com.dcy.service.bizfile.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *   文件表
 * </p>
 *
 * @author dcy
 * @since 2019-09-18
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "FileInfo对象", description = "")
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件md5")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "是否是图片类型")
    private Boolean isImg;

    @ApiModelProperty(value = "文件类型")
    private String contentType;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "物理路径")
    private String path;

    @ApiModelProperty(value = "url地址")
    private String url;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "创建时间")
    private String createDate;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String IS_IMG = "is_img";

    public static final String CONTENT_TYPE = "content_type";

    public static final String SIZE = "size";

    public static final String PATH = "path";

    public static final String URL = "url";

    public static final String SOURCE = "source";

    public static final String CREATE_DATE = "createDate";


}
