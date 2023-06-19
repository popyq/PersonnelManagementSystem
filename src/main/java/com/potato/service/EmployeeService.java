package com.potato.service;

import com.potato.dto.EmployeeDTO;
import com.potato.dto.requestDTO.EmployeeListRequestDTO;
import com.potato.dto.requestDTO.ToggleEmployeeRequestDTO;
import com.potato.dto.responseDTO.EmployeeResponseDTO;
import com.potato.entity.Employee;

import java.util.Map;

/**
 * 员工表(Employee)表服务接口
 * @author makejava
 */
public interface EmployeeService {

    /**
     * 通过账户名密码联合查询员工详细信息
     * @param username
     * @param password
     * @return
     */
    EmployeeDTO getEmInfoByUserNameAndPassword(String username, String password);

    /**
     * 查询员工列表
     * @param requestDTO
     * @return
     */
    Map<String, Object> querPageList(EmployeeListRequestDTO requestDTO);

    /**
     * 新增员工
     * @param employee
     */
    void insert(Employee employee);

    /**
     * 对单个职位状态进行切换
     * @param requestDTO
     * @return
     */
    Map<String, Object> toggleStatus(ToggleEmployeeRequestDTO requestDTO);

    /**
     * 根据id查询 单个员工数据
     * @param employeeId
     * @return
     */
    EmployeeResponseDTO queryById(Integer employeeId) throws Exception;

    /**
     * 修改员工信息
     * @param employee
     */
    void update(Employee employee);
}

