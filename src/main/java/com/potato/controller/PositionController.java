package com.potato.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.dto.requestDTO.PositionListRequestDTO;
import com.potato.dto.requestDTO.TogglePositionRequestDTO;
import com.potato.entity.Position;
import com.potato.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author potato
 * @PackageName:com.potato.controller
 * @Description: 职位模块
 */
@Controller
@RequestMapping("/position")
@Api(tags = "职位管理模块")
public class PositionController extends BaseController {

    @Autowired
    private PositionService positionService;


    @ApiOperation(value = "跳转至职位列表的路由")
    @GetMapping("/toList")
    public String toList() {
        request.getSession().setAttribute("pageName", "职位管理");
        return "position-list";
    }


    @ApiOperation(value = "跳转至新增职位的路由")
    @GetMapping("/toAddPosition")
    public String toAddPosition() {
        request.getSession().setAttribute("pageName", "新增管理");
        return "position-Add";
    }


    @ApiOperation(value = "跳转至查看职位的信息路由")
    @GetMapping("/goPosition")
    public String goPosition() {
        request.getSession().setAttribute("pageName", "职位详情");
        return "position-info";
    }


    @ApiOperation(value = "修改职位信息")
    @GetMapping("/goPositionEdit")
    public String goPositionEdit() {
        request.getSession().setAttribute("pageName", "修改职位信息");
        return "position-edit";
    }


    @ApiOperation(value = "查询列表数据(包括分页,模糊查询,条件查询)")
    @PostMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(@RequestBody PositionListRequestDTO requestDTO) {
        Map<String, Object> map = null;
        try {
            // 调用职位的service层去查询数据
            map = positionService.querPageList(requestDTO);
        } catch (Exception e) {
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }


    @ApiOperation(value = "对单个职位状态进行切换")
    @PostMapping("/togglePositionStatus")
    @ResponseBody
    public Map<String, Object> togglePositionStatus(@RequestBody TogglePositionRequestDTO requestDTO) {
        Map<String, Object> map = null;
        try {
                map = positionService.toggleStatus(requestDTO);
        } catch (Exception e) {
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }


    @ApiOperation(value = "查看指定职位信息")
    @PostMapping("/queryPositionById")
    @ResponseBody
    public Map<String, Object> queryPositionById(Integer positionId) {
        Map<String, Object> map = new HashMap<>();
        // 用Integer类型可以接收, 会自动帮你转换
        System.out.println("positionId:" + positionId);
        Position position = null;
        try {
            position = positionService.queryById(positionId);

        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        map.put("data", position);
        return map;
    }


    @ApiOperation(value = "新增职位信息")
    @PostMapping("/insertPosition")
    @ResponseBody
    public Map<String, Object> insertPosition(@RequestParam String positionStr) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Position position = null;
        System.out.println(position);
        try {
            position = mapper.readValue(positionStr, Position.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("errMsg", "传入的Json字符串格式有问题");
            return map;
        }
        try {
            System.out.println(position);
            if (position.getPositionName() != "") {
                positionService.insert(position);
            } else {
                map.put("success", false);
                map.put("errMsg", "操作失败");
                return map;
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        return map;
    }


    @ApiOperation(value = "编辑并保存职位信息")
    @PostMapping("/editPositionById")
    @ResponseBody
    public Map<String, Object> editPositionById(@RequestParam String positionStr) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Position position = null;
        try {
            position = mapper.readValue(positionStr, Position.class);
        } catch (JsonProcessingException e) {
            map.put("success", false);
            map.put("errMsg", "传入的Json字符串格式有问题");
            return map;
        }
        try {
            System.out.println(position);
            if (position.getPositionName() != "" ) {
                positionService.update(position);
            } else {
                map.put("success", false);
                map.put("errMsg", "操作失败");
                return map;
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        return map;
    }


    @ApiOperation(value = "查询 status=1 的列表数据")
    @PostMapping("/queryActivePositionList")
    @ResponseBody
    public Map<String, Object> queryActivePositionList() {
        Map<String, Object> map = null;
        try {
            // 调用部门的service层去查询数据
            map = positionService.queryActivePositionList();
        } catch (Exception e) {
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }
}
