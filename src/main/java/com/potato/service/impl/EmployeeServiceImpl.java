package com.potato.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potato.dao.DepartmentDao;
import com.potato.dao.EmployeeDao;
import com.potato.dao.PositionDao;
import com.potato.dto.EmployeeDTO;
import com.potato.dto.requestDTO.EmployeeListRequestDTO;
import com.potato.dto.requestDTO.ToggleEmployeeRequestDTO;
import com.potato.dto.responseDTO.ActiveDepartmentListResponseDTO;
import com.potato.dto.responseDTO.ActivePositionListResponseDTO;
import com.potato.dto.responseDTO.EmployeeResponseDTO;
import com.potato.entity.Employee;
import com.potato.service.EmployeeService;
import com.potato.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 员工表(Employee)表服务实现类
 * @author makejava
 */
@Service("employeeService")
public class EmployeeServiceImpl  implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private PositionDao positionDao;

    /**
     * 通过账户名密码联合查询员工详细信息
     * @param username
     * @param password
     * @return
     */
    @Override
    public EmployeeDTO getEmInfoByUserNameAndPassword(String username, String password) {
        return employeeDao.queryEmInfoByUserNameAndPassword( username, MD5Util.getMd5(password));
    }

    @Override
    public Map<String, Object> querPageList(EmployeeListRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 封装查询条件
            QueryWrapper<EmployeeListRequestDTO> wrapper = new QueryWrapper<>();
            if (null != requestDTO.getName() && !"".equals(requestDTO.getName())) {
                wrapper.like("name",requestDTO.getName());
            }if (Objects.nonNull(requestDTO.getDepId())) {
                wrapper.eq("dep_id", requestDTO.getDepId());
            }
            if (Objects.nonNull(requestDTO.getPositionId())) {
                wrapper.eq("position_id",requestDTO.getPositionId());
            }
            if (Objects.nonNull(requestDTO.getStatus())) {
                wrapper.eq("status",requestDTO.getStatus());
            }
            Page<EmployeeListRequestDTO> page = new Page<>(requestDTO.getCurrent(),requestDTO.getSize());
            //封装查询条件end
            IPage<EmployeeDTO> department = employeeDao.queryPageList(page, wrapper);
            map.put("success",true);
            map.put("data",department);
        }catch (Exception e){
            map.put("success",false);
            map.put("errMsg",e.getMessage());
            //如果 用到了Spring事务申明,在catch中捕获异常之后,一定要抛出,否则事务失效
        }
        return map;
    }

    /**
     * 新增员工
     * @param employee
     */
    @Override
    public void insert(Employee employee) {
        employee.setPassword(MD5Util.getMd5("123456"));
        employeeDao.insert(employee);
    }

    /**
     * 对单个职位状态进行切换
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Map<String, Object> toggleStatus(ToggleEmployeeRequestDTO requestDTO) {
            Map<String,Object> map= new HashMap<>();
            // 该部门当前状态
            Integer status = requestDTO.getStatus();
            Integer depId = requestDTO.getEmId();
            try {
                if (status == 1) {
                    // 将状态该为禁用
                    employeeDao.updateFailureStatusById(depId);
                    //  throw new Exception("测试异常");
                }else {
                    employeeDao.updateSuccessStatusById(depId);
                }
                map.put("success",true);
            }catch (Exception e){
                // 应该抛出自定义异常
                throw e;
            }
            return map;
    }

    /**
     * 根据id查询 单个员工数据
     * @param employeeId
     * @return
     */
    @Override
    public EmployeeResponseDTO queryById(Integer employeeId) throws Exception {
        try {
            Employee employee = employeeDao.selectById(employeeId);
            if (Objects.isNull(employee)) {
                throw new Exception("没有查询到员工信息");
            }
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            List<ActivePositionListResponseDTO> activePositionList = positionDao.queryActivePositionList();
            List<ActiveDepartmentListResponseDTO> activeDepartmentList = departmentDao.queryActiveDepartmentList();
            responseDTO.setEmployee(employee);
            responseDTO.setActiveDepartmentList(activeDepartmentList);
            responseDTO.setActivePositionList(activePositionList);
            return responseDTO;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 修改员工信息
     * @param employee
     */
    @Override
    public void update(Employee employee) {
        // 这是mybatisplus的
        employeeDao.updateById(employee);
    }
}

