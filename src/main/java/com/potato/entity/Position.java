package com.potato.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位表(Position)实体类
 * @author makejava
 */

@Data
@TableName("position")
public class Position implements Serializable {

    private static final long serialVersionUID = -79728262245282845L;

    /**
     * 职位表id
     */
    private Integer positionId;

    /**
     * 职位名
     */
    private String positionName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态 1:有效 0:无效
     */
    private Integer status;
}

