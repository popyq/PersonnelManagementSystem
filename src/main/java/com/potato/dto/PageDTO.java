package com.potato.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto
 * @Description: 分页DTO
 */
@Data
public class PageDTO implements Serializable {


    private static final long serialVersionUID = -806772252970934406L;

    /**
     * 数据总量
     */
    @ApiModelProperty(value = "数据总量")
    private Long total;

    @ApiModelProperty(value = "总页数")
    private Long pages;

    @ApiModelProperty(value = "当前页码")
    private Long current = 1L;

    @ApiModelProperty(value = "每页多少")
    private Long size = 10L ;
}
