package com.potato.controller;

import com.potato.dto.EmployeeDTO;
import com.potato.dto.requestDTO.LoginRequestDTO;
import com.potato.service.EmployeeService;
import com.potato.util.CodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author potato
 * @PackageName:com.potato.controller
 * @Description: 登录登出
 */
@Api(tags = "登录登出")
@Controller
public class IndexController extends BaseController {


    @Autowired
    private EmployeeService employeeService;


    @ApiOperation(value = "跳转至登录页面")
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }


    @ApiOperation(value = "登录验证")
    @PostMapping("/checkLogin")
    @ResponseBody
    public Map<String, Object> checkLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        System.out.println("请求进来了");
        Map<String, Object> modelMap = new HashMap<>();
        // 是否需要进行验证码的验证
        Boolean needVerify = loginRequestDTO.getNeedVerify();
        if (needVerify && !CodeUtil.checkVerifyCode(request, loginRequestDTO.getVerifyCodeActual())) {
            // 验证用户输入的验证码是否正确
            modelMap.put("success", false);
            modelMap.put("errMsg", "验证码错误");
            return modelMap;
        }
        // 获取用户名
        String username = loginRequestDTO.getUserName();
        // 获取用户输入的面
        String password = loginRequestDTO.getPassword();
        // 有一个非空校验的工具 validator
        if (username != null && password != null) {
            // 通过用户名密码去查库
            EmployeeDTO userInfo = employeeService.getEmInfoByUserNameAndPassword(username, password);
            if (null != userInfo) {
                // 如果根据账号密码查询到的对象不为null,则代表登录成功
                modelMap.put("success", true);
                modelMap.put("username", userInfo.getName());
                // 同时将用户信息存入session
                request.getSession().setAttribute("userInfo", userInfo);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "用户名密码错误");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名密码均不能为空");
        }
        return modelMap;
    }


    @ApiOperation(value = "跳转主页面")
    @GetMapping(value = "/main")
    public String toMain() {
        request.getSession().setAttribute("pageName", "后台首页");
        return "main";
    }


    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    @ResponseBody
    public Map<String, Object> logout() {
        Map<String, Object> modelMap = new HashMap<>();
        request.getSession().setAttribute("userInfo", null);
        modelMap.put("success", true);
        return modelMap;
    }
}
