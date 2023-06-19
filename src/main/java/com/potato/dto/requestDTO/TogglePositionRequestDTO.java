package com.potato.dto.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.requestDTO
 * @Description: 切换单个职位状态的RequestDTO
 */

@Data
@ApiModel(description = "切换单个职位状态的RequestDTO")
public class TogglePositionRequestDTO implements Serializable{

    private static final long serialVersionUID = -8389732776754181094L;

    @ApiModelProperty(value = "职位id",example = "")
    private Integer positionId;

    @ApiModelProperty(value = "状态 1:启用; 0 :禁用",example = "")
    private Integer status;
}
