package com.potato.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author potato
 * @PackageName:com.potato.dto.responseDTO
 * @Description: 职位列表ResponseDTO
 */
@Data
@ApiModel(description = "职位列表ResponseDTO")
@Controller
public class PositionListResponseDTO implements Serializable {


    private static final long serialVersionUID = -796975344543518588L;

    @ApiModelProperty(value = "职位id",example = "")
    private Integer positionId;

    @ApiModelProperty(value = "职位名称",example = "")
    private String positionName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间",example = "")
    private Date createTime;

    @ApiModelProperty(value = "状态, 1代表有效,0代表无效 ",example = "")
    private Integer status;

}
