package com.potato.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.potato.dto.requestDTO.EmployeeListRequestDTO;
import com.potato.dto.requestDTO.ToggleEmployeeRequestDTO;
import com.potato.dto.responseDTO.EmployeeResponseDTO;
import com.potato.entity.Employee;
import com.potato.service.EmployeeService;
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
 * @Description:  员工模块
 */
@Controller
@RequestMapping("/employee")
@Api(tags="员工管理模块")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService employeeService;


    @ApiOperation(value = "跳转至员工列表的路由")
    @GetMapping("/toList")
    public String toList() {
        request.getSession().setAttribute("pageName", "员工管理");
            return "employee-list";
    }


    @ApiOperation(value = "跳转至新增员工的路由")
    @GetMapping("/toAddEmployee")
    public String toAddEmployee() {
        request.getSession().setAttribute("pageName", "新增管理");
        return "employee-Add";
    }


    @ApiOperation(value = "跳转至查看员工的信息路由")
    @GetMapping("/goEmployee")
    public String goEmployee() {
        request.getSession().setAttribute("pageName", "员工详情");
        return "employee-info";
    }


    @ApiOperation(value = "修改员工信息")
    @GetMapping("/goEmployeeEdit")
    public String goEmployeeEdit() {
        request.getSession().setAttribute("pageName", "修改员工信息");
        return "employee-edit";
    }


    @ApiOperation(value = "查询列表数据(包括分页,模糊查询,条件查询)")
    @PostMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(@RequestBody EmployeeListRequestDTO requestDTO) {
        Map<String, Object> map = null;
        try {
            // 调用职位的service层去查询数据
            map = employeeService.querPageList(requestDTO);
        } catch (Exception e) {
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }


    @ApiOperation(value = "新增员工信息")
    @PostMapping("/insertEmployee")
    @ResponseBody
    public Map<String, Object> insertEmployee(@RequestParam String employeeStr) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = null;
        try {
            employee = mapper.readValue(employeeStr, Employee.class);
        } catch (JsonProcessingException e) {
            map.put("success", false);
            map.put("errMsg", "传入的Json字符串格式有问题");
            return map;
        }
        try {
                employeeService.insert(employee);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        return map;
    }


    @ApiOperation(value = "对单个员工状态进行切换")
    @PostMapping("/toggleEmployee")
    @ResponseBody
    public Map<String, Object> toggleEmployeeStatus(@RequestBody ToggleEmployeeRequestDTO requestDTO) {
        Map<String, Object> map = null;
        try {
            map = employeeService.toggleStatus(requestDTO);
        } catch (Exception e) {
            map = new HashMap<>();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }


    @ApiOperation(value = "查看指定员工信息")
    @PostMapping("/queryEmployeeById")
    @ResponseBody
    public Map<String, Object> queryDepartmentById(Integer employeeId) {
        Map<String, Object> map = new HashMap<>();
        // 用Integer类型可以接收, 会自动帮你转换
        System.out.println("employeeId:" + employeeId);
        EmployeeResponseDTO responseDTO = null;
        try {
            responseDTO = employeeService.queryById(employeeId);

        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        map.put("data", responseDTO);
        return map;

    }


    @ApiOperation(value = "编辑并保存员工信息")
    @PostMapping("/editEmployeeById")
    @ResponseBody
    public Map<String, Object> editDepartmentById(@RequestParam String employeeStr) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = null;
        try {
            employee = mapper.readValue(employeeStr, Employee.class);
        } catch (JsonProcessingException e) {
            map.put("success", false);
            map.put("errMsg", "传入的Json字符串格式有问题");
            return map;
        }
        try {
                employeeService.update(employee);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        return map;
    }


}
