package com.potato.dto.requestDTO;

import com.potato.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.requestDTO
 * @Description: 获取员工列表RequestDTO
 */

@Data
@ApiModel(description = "获取员工列表RequestDTO")
public class EmployeeListRequestDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -8962499329584683729L;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名",example = "")
    private String name;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id",example = "")
    private Integer depId;

    /**
     * 职位id
     */
    @ApiModelProperty(value = "职位id",example = "")
    private Integer positionId;

    /**
     * 状态(0:离职 1:在职)
     */
    @ApiModelProperty(value = "状态(0:离职 1:在职)",example = "")
    private Integer status;
}
