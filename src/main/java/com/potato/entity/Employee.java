package com.potato.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 员工表(Employee)实体类
 * @author makejava
 */
@Data
@TableName("employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = -67334813503999362L;

    /**
     * 员工id
     */
    @TableId(type = IdType.AUTO)
    private Integer emId;

    /**
     * 密码
     */
    private String password;

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

}

