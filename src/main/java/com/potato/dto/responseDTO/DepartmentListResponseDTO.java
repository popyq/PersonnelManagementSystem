package com.potato.dto.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.responseDTO
 * @Description: 部门列表ResponseDTO
 */

@Data
@ApiModel(description = "部门列表ResponseDTO")
@Controller
public class DepartmentListResponseDTO  implements Serializable {

    private static final long serialVersionUID = -9214823523689390703L;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id",example = "")
    private Integer depId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称",example = "")
    private String name;

    /**
     * 办公地点
     */
    @ApiModelProperty(value = "办公地点",example = "")
    private String address;

    /**
     * 1代表有效,0代表无效
     */
    @ApiModelProperty(value = "状态, 1代表有效,0代表无效 ",example = "")
    private Integer status;

}
