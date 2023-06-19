package com.potato.service;

import com.potato.dto.requestDTO.DepartmentListRequestDTO;
import com.potato.dto.requestDTO.ToggleDepartmentRequestDTO;
import com.potato.entity.Department;

import java.util.Map;

/**
 * 部门表(Department)表服务接口
 * @author makejava
 */
public interface DepartmentService {

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
     * @return 实例对象
     */
    Department insert(Department department);

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    Department update(Department department);


    /**
     * 查询部门列表
     * @return
     */
    Map<String, Object> querPageList(DepartmentListRequestDTO requestDTO);

    /**
     * 对单个部门状态进行切换
     * @param requestDTO
     * @return
     */
    Map<String, Object> toggleStatus(ToggleDepartmentRequestDTO requestDTO) throws Exception;

    /**
     * 查询所有有效部门列表
     * @return
     */
    Map<String, Object> queryActiveDepartmentList();

}
