package com.potato.dto.requestDTO;

import com.potato.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.requestDTO
 * @Description: 获取部门列表RequestDTO
 */
@Data
@ApiModel(description = "获取部门列表RequestDTO")
public class DepartmentListRequestDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -8983372007375493270L;

    @ApiModelProperty(value = "部门名称",example = "")
    private String name;

    @ApiModelProperty(value = "部门地址",example = "")
    private String address;

    @ApiModelProperty(value = "状态:1 代表有效,0 代表无效",example = "")
    private Integer status;
}
