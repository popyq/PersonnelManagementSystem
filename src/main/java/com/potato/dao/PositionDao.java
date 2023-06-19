package com.potato.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potato.dto.requestDTO.PositionListRequestDTO;
import com.potato.dto.responseDTO.ActivePositionListResponseDTO;
import com.potato.dto.responseDTO.PositionListResponseDTO;
import com.potato.entity.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职位表(Position)表数据库访问层
 * @author makejava
 */
public interface PositionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param positionId 主键
     * @return 实例对象
     */
    Position queryById(Integer positionId);


    /**
     * 新增数据
     * @param position 实例对象
     * @return 影响行数
     */
    int insert(Position position);


    /**
     * 修改数据
     * @param position 实例对象
     * @return 影响行数
     */
    int update(Position position);


    /**
     * 分页+筛选查询职位列表使用MybatisPlus分页
     * @param page
     * @return
     */
    IPage<PositionListResponseDTO> queryPageList(Page<PositionListRequestDTO> page, @Param(Constants.WRAPPER) Wrapper<PositionListRequestDTO> queryWrapper );

    /**
     * 更改status状态
     * @param positionId
     */
    void updateFailureStatusById(Integer positionId);

    /**
     * 更改status状态
     * @param positionId
     */
    void updateSuccessStatusById(Integer positionId);

    /**
     * 根据状态查询有效的职位信息
     * @return
     */
    List<ActivePositionListResponseDTO> queryActivePositionList();
}

