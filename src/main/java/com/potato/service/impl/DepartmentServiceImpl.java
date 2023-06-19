package com.potato.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potato.dao.DepartmentDao;
import com.potato.dto.requestDTO.DepartmentListRequestDTO;
import com.potato.dto.requestDTO.ToggleDepartmentRequestDTO;
import com.potato.dto.responseDTO.ActiveDepartmentListResponseDTO;
import com.potato.dto.responseDTO.DepartmentListResponseDTO;
import com.potato.entity.Department;
import com.potato.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门表(Department)表服务实现类
 * @author makejava
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentDao departmentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param depId 主键
     * @return 实例对象
     */
    @Override
    public Department queryById(Integer depId) {
        return this.departmentDao.queryById(depId);
    }



    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department insert(Department department) {
        this.departmentDao.insert(department);
        return department;
    }

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department update(Department department) {
        this.departmentDao.update(department);
        return this.queryById(department.getDepId());
    }


    /**
     * 分页
     * @param requestDTO
     * @return
     */
    @Override
    public Map<String, Object> querPageList(DepartmentListRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 封装查询条件
            QueryWrapper<DepartmentListRequestDTO> wrapper = new QueryWrapper<>();
            if ( null != requestDTO.getName()){
                wrapper.like("name",requestDTO.getName());
            }else if (null != requestDTO.getAddress()){
                wrapper.like("address",requestDTO.getAddress());
            }
            if (null != requestDTO.getStatus()) {
                wrapper.eq("status",requestDTO.getStatus());
            }
            Page<DepartmentListRequestDTO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
            //封装查询条件end
            IPage<DepartmentListResponseDTO> department = departmentDao.queryPageList(page, wrapper);
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
     * 对单个部门状态进行切换
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Map<String, Object> toggleStatus(ToggleDepartmentRequestDTO requestDTO) throws Exception {
        Map<String,Object> map= new HashMap<>();
        // 该部门当前状态
        Integer status = requestDTO.getStatus();
        Integer depId = requestDTO.getDepId();
        try {
            if (status == 1) {
                // 将状态该为禁用
                departmentDao.updateFailureStatusById(depId);
//                throw new Exception("测试异常");
            }else {
                departmentDao.updateSuccessStatusById(depId);
            }
            map.put("success",true);
        }catch (Exception e){
            // 应该抛出自定义异常
            throw e;
        }
        return map;
    }

    /**
     * 查询所有有效部门列表
     * @return
     */
    @Override
    public Map<String, Object> queryActiveDepartmentList() {
        Map<String,Object> map= new HashMap<>();
        try {
           List<ActiveDepartmentListResponseDTO>  activeDepartmentList=  departmentDao.queryActiveDepartmentList();
            map.put("success",true);
            map.put("data",activeDepartmentList);
        }catch (Exception e) {
            map.put("success",false);
            map.put("errMsg",e.getMessage());
        }
        return map;
    }
}
