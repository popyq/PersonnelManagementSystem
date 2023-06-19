package com.potato.dto.requestDTO;

import com.potato.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.requestDTO
 * @Description: 获取职位列表RequestDTO
 */

@Data
@ApiModel(description = "获取职位列表RequestDTO")
public class PositionListRequestDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = 7772380027152653292L;

    @ApiModelProperty(value = "职位名称",example = "")
    private String positionName;

    @ApiModelProperty(value = "状态, 1代表有效,0代表无效 ",example = "")
    private Integer status;
}
