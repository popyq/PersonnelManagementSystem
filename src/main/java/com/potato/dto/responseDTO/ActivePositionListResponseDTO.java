package com.potato.dto.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.responseDTO
 * @Description: 有效的职位列表ResponseDTO
 */

@Data
@ApiModel(description = "有效的职位列表ResponseDTO,下拉列表用")
public class ActivePositionListResponseDTO implements Serializable {

    private static final long serialVersionUID = -5467395270143507300L;

    /**
     * 职位表id
     */
    @ApiModelProperty(value = "职位表id")
    private Integer positionId;

    /**
     * 职位名
     */
    @ApiModelProperty(value = "职位名")
    private String positionName;
}
