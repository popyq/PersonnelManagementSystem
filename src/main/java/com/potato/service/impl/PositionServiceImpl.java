package com.potato.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potato.dao.PositionDao;
import com.potato.dto.requestDTO.PositionListRequestDTO;
import com.potato.dto.requestDTO.TogglePositionRequestDTO;
import com.potato.dto.responseDTO.ActivePositionListResponseDTO;
import com.potato.dto.responseDTO.PositionListResponseDTO;
import com.potato.entity.Position;
import com.potato.service.PositionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 职位表(Position)表服务实现类
 * @author makejava
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService {

    @Resource
    private PositionDao positionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param positionId 主键
     * @return 实例对象
     */
    @Override
    public Position queryById(Integer positionId) {
        return this.positionDao.queryById(positionId);
    }


    /**
     * 新增数据
     * @param position 实例对象
     * @return 实例对象
     */
    @Override
    public Position insert(Position position) {
        //  position.setCreateTime(new Date());
        this.positionDao.insert(position);
        return position;
    }

    /**
     * 修改数据
     * @param position 实例对象
     * @return 实例对象
     */
    @Override
    public Position update(Position position) {
        this.positionDao.update(position);
        return this.queryById(position.getPositionId());
    }


    /**
     * 查询职位列表 +分页
     * @param requestDTO
     * @return
     */
    @Override
    public Map<String, Object> querPageList(PositionListRequestDTO requestDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 封装查询条件
            QueryWrapper<PositionListRequestDTO> wrapper = new QueryWrapper<>();
            if ( null != requestDTO.getPositionName()){
                wrapper.like("position_name",requestDTO.getPositionName());
            }
            if (null != requestDTO.getStatus()) {
                wrapper.eq("status",requestDTO.getStatus());
            }
            Page<PositionListRequestDTO> page = new Page<>(requestDTO.getCurrent(), requestDTO.getSize());
            //封装查询条件end
            IPage<PositionListResponseDTO> department = positionDao.queryPageList(page, wrapper);
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
     * 对单个职位状态进行切换
     * @param requestDTO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Map<String, Object> toggleStatus(TogglePositionRequestDTO requestDTO) {
        Map<String,Object> map= new HashMap<>();
        // 该部门当前状态
        Integer status = requestDTO.getStatus();
        Integer positionId = requestDTO.getPositionId();
        try {
            if (status == 1) {
                // 将状态该为禁用
                positionDao.updateFailureStatusById(positionId);
            }else {
                positionDao.updateSuccessStatusById(positionId);
            }
            map.put("success",true);
        }catch (Exception e){
            // 应该抛出自定义异常
            e.printStackTrace();
//            throw e;
        }
        return map;
    }

    /**
     * 查询所有有效部门列表
     * @return
     */
    @Override
    public Map<String, Object> queryActivePositionList() {
        Map<String,Object> map= new HashMap<>();
        try {
            List<ActivePositionListResponseDTO> activePositionList=  positionDao.queryActivePositionList();
            map.put("success",true);
            map.put("data",activePositionList);
        }catch (Exception e) {
            map.put("success",false);
            map.put("errMsg",e.getMessage());
        }
        return map;
    }
}
