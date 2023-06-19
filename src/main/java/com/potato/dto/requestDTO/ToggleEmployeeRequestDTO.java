package com.potato.dto.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.requestDTO
 * @Description: 切换单个员工状态的RequestDTO
 */
@Data
@ApiModel(description = "切换单个员工状态的RequestDTO")
public class ToggleEmployeeRequestDTO implements Serializable {

    private static final long serialVersionUID = 6762522807287804442L;

    @ApiModelProperty(value = "员工id",example = "")
    private Integer emId;

    @ApiModelProperty(value = "状态 1:启用; 0 :禁用",example = "")
    private Integer status;

}
