package com.potato.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.potato.entity.Department;
import com.potato.entity.Position;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dao
 * @Description: 员工DTO
 */
@Data
public class EmployeeDTO implements Serializable {



    /**
     * 员工id
     */
    @TableId(type = IdType.AUTO)
    private Integer emId;
    /**
     * 密码 可以不用显示
     */
    // private String password;
    /**
     * 姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 部门id
     */
    private Integer depId;

    /**
     * 职位id
     */
    private Integer positionId;

    /**
     * 状态(0:离职 1:在职)
     */
    private Integer status;

    /**
     * 一对一关联部门
     */
    @ApiModelProperty(value="对应的部门信息")
    private Department department;

    /**
     * 一对一关联职位
     */
    @ApiModelProperty(value="对应的职位信息")
    private Position position;


}
