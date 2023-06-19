package com.potato.dto.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.responseDTO
 * @Description: 有效的部门列表ResponseDTO
 */

@Data
@ApiModel(description = "有效的部门列表ResponseDTO,下拉列表用")
public class ActiveDepartmentListResponseDTO implements Serializable {
    private static final long serialVersionUID = 1759768109396029298L;

    @ApiModelProperty(value = "部门id")
    private Integer depId;

    @ApiModelProperty(value = "部门名称")
    private String name;
}
