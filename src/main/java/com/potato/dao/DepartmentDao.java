package com.potato.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potato.dto.requestDTO.DepartmentListRequestDTO;
import com.potato.dto.responseDTO.ActiveDepartmentListResponseDTO;
import com.potato.dto.responseDTO.DepartmentListResponseDTO;
import com.potato.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表(Department)表数据库访问层
 * @author makejava
 */
public interface DepartmentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param depId 主键
     * @return 实例对象
     */
    Department queryById(Integer depId);


    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 影响行数
     */
    int insert(Department department);


    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 影响行数
     */
    int update(Department department);


    /**
     * 分页+筛选查询部门列表使用MybatisPlus分页
     * @param page
     * @return
     */
    IPage<DepartmentListResponseDTO> queryPageList(Page<DepartmentListRequestDTO> page, @Param(Constants.WRAPPER)Wrapper<DepartmentListRequestDTO> queryWrapper );

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


    /**
     * 根据状态查询有效的部门信息
     * @return
     */
    List<ActiveDepartmentListResponseDTO> queryActiveDepartmentList();
}

