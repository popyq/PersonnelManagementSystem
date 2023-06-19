package com.potato.service;

import com.potato.dto.requestDTO.PositionListRequestDTO;
import com.potato.dto.requestDTO.TogglePositionRequestDTO;
import com.potato.entity.Position;

import java.util.Map;


/**
 * 职位表(Position)表服务接口
 * @author makejava
 */
public interface PositionService {

    /**
     * 通过ID查询单条数据
     *
     * @param positionId 主键
     * @return 实例对象
     */
    Position queryById(Integer positionId);


    /**
     * 新增数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    Position insert(Position position);

    /**
     * 修改数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    Position update(Position position);

    /**
     * 查询职位列表
     * @param requestDTO
     * @return
     */
    Map<String, Object> querPageList(PositionListRequestDTO requestDTO);

    /**
     * 对单个职位状态进行切换
     * @param requestDTO
     * @return
     */
    Map<String, Object> toggleStatus(TogglePositionRequestDTO requestDTO);

    /**
     * 查询所有有效部门列表
     * @return
     */
    Map<String, Object> queryActivePositionList();
}
