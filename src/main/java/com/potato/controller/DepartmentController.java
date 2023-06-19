package com.potato.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.dto.requestDTO.DepartmentListRequestDTO;
import com.potato.dto.requestDTO.ToggleDepartmentRequestDTO;
import com.potato.entity.Department;
import com.potato.service.DepartmentService;
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
 * @Description: 部门模块
 */
@Controller
@RequestMapping("/department")
@Api(tags = "部门管理模块")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;


    @ApiOperation(value = "跳转至部门列表的路由")
    @GetMapping("/toList")
    public String toList() {
        request.getSession().setAttribute("pageName", "部门管理");
        return "department-list";
    }


    @ApiOperation(value = "跳转至新增列表的路由")
    @GetMapping("/toAddDepartment")
    public String toAddDepartment() {
        request.getSession().setAttribute("pageName", "新增管理");
        return "department-Add";
    }


    @ApiOperation(value = "跳转至查看部门的信息路由")
    @GetMapping("/goDepartment")
    public String goDepartment() {
        request.getSession().setAttribute("pageName", "部门详情");
        return "department-info";
    }


    @ApiOperation(value = "修改部门信息")
    @GetMapping("/goDepartmentEdit")
    public String goDepartmentEdit() {
        request.getSession().setAttribute("pageName", "修改部门信息");
        return "department-edit";
    }


    @ApiOperation(value = "查询列表数据(包括分页,模糊查询,条件查询)")
    @PostMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(@RequestBody  DepartmentListRequestDTO requestDTO) {
        Map<String, Object> map = null;
        try {
            // 调用部门的service层去查询数据
            map = departmentService.querPageList(requestDTO);
        } catch (Exception e) {
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }


    @ApiOperation(value = "对单个部门状态进行切换")
    @PostMapping("/toggleDepartment")
    @ResponseBody
    public Map<String, Object> toggleDepartmentStatus(@RequestBody ToggleDepartmentRequestDTO requestDTO) {
        Map<String, Object> map = null;
        try {
            map = departmentService.toggleStatus(requestDTO);
        } catch (Exception e) {
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }


    @PostMapping("/queryDepartmentById")
    @ApiOperation(value = "查看指定部门信息")
    @ResponseBody
    public Map<String, Object> queryDepartmentById(Integer departmentId) {
        Map<String, Object> map = new HashMap<>();
        // 用Integer类型可以接收, 会自动帮你转换
        System.out.println("departmentId:" + departmentId);
        Department department = null;
        try {
            department = departmentService.queryById(departmentId);

        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        map.put("data", department);
        return map;
    }


    @ApiOperation(value = "新增部门信息")
    @PostMapping ("/insertDepartment")
    @ResponseBody
    public Map<String, Object> insertDepartment(@RequestParam String departmentStr) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Department department = null;
        try {
            department = mapper.readValue(departmentStr, Department.class);
        } catch (JsonProcessingException e) {
            map.put("success", false);
            map.put("errMsg", "传入的Json字符串格式有问题");
            return map;
        }
        try {
            System.out.println(department);
            if (department.getName() != "" && department.getAddress() != "") {
                departmentService.insert(department);
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


    @ApiOperation(value = "编辑并保存部门信息")
    @PostMapping("/editDepartmentById")
    @ResponseBody
    public Map<String, Object> editDepartmentById(@RequestParam String departmentStr) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Department department = null;
        try {
            department = mapper.readValue(departmentStr, Department.class);
        } catch (JsonProcessingException e) {
            map.put("success", false);
            map.put("errMsg", "传入的Json字符串格式有问题");
            return map;
        }
        try {
            System.out.println(department);
            if (department.getName() != "" && department.getAddress() != "") {
                departmentService.update(department);
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
    @PostMapping("/queryActiveDepartmentList")
    @ResponseBody
    public Map<String, Object> queryActiveDepartmentList() {
        Map<String, Object> map = null;
        try {
            // 调用部门的service层去查询数据
            map = departmentService.queryActiveDepartmentList();
        } catch (Exception e) {
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }


}

