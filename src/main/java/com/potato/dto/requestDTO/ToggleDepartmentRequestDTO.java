package com.potato.dto.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.requestDTO
 * @Description: 切换单个部门状态的RequestDTO
 */
@Data
@ApiModel(description = "切换单个部门状态的RequestDTO")
public class ToggleDepartmentRequestDTO implements Serializable {

    private static final long serialVersionUID = -2225392882558450426L;

    @ApiModelProperty(value = "部门id",example = "")
    private Integer depId;

    @ApiModelProperty(value = "状态 1:启用; 0 :禁用",example = "")
    private Integer status;
}
