package com.potato.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potato.dto.EmployeeDTO;
import com.potato.dto.requestDTO.EmployeeListRequestDTO;
import com.potato.entity.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * 员工表(Employee)表数据库访问层
 * @author makejava
 */
public interface EmployeeDao extends BaseMapper<Employee> {

    /**
     * 根据账号和密码查询员工信息
     * @param userName
     * @param password
     * @return
     */
    EmployeeDTO queryEmInfoByUserNameAndPassword(@Param("userName") String userName, @Param("password")String password);

    /**
     * 分页+筛选查询员工列表使用MybatisPlus分页
     * @param page
     * @return
     */
    IPage<EmployeeDTO> queryPageList(Page<EmployeeListRequestDTO> page, @Param(Constants.WRAPPER) Wrapper<EmployeeListRequestDTO> wrapper);

    /**
     * 更改status状态
     * @param depId
     */
    void updateFailureStatusById(Integer depId);

    /**
     * 更改status状态
     * @param depId
     */
    void updateSuccessStatusById(Integer depId);
}

