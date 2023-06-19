package com.potato.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门表(Department)实体类
 * @author makejava
 */
@Data
@TableName("department")
public class Department implements Serializable {

    private static final long serialVersionUID = 263621435999725444L;
    /**
     * 部门id
     */
    private Integer depId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 办公地点
     */
    private String address;
    /**
     * 1代表有效,0代表无效
     */
    private Integer status;


}

