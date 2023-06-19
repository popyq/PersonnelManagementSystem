package com.potato.interceptor;

import com.potato.dto.EmployeeDTO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author potato
 * @PackageName:com.potato.interceptor
 * @Description: 登录验证
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截的请求:"+request.getRequestURI());
        // 从session里取用户信息
        Object userObj = request.getSession().getAttribute("userInfo");
        /**
         * 如果用户信息不为空,那么需要判断员工是否在职
         */
        if (  null  != userObj ) {
            EmployeeDTO userInfo = (EmployeeDTO) userObj;
            // 确保员工id不为null,且该员工是在职状态
            if(userInfo.getEmId() != null && userInfo.getStatus() == 1){
                // 如果用户已经登录,用户这个时候url是/或者/login,就自动跳转至main
                if ( request.getRequestURI().toLowerCase().indexOf("login") >=0 || "/".equals(request.getRequestURI())) {
                    response.sendRedirect("/main");
                    return false;
                }
                return true;
            }
        }

        // 如果没有获取到员工信息,如果请求路径是/login 或者 /,可以放行
        if (request.getRequestURI().toLowerCase().indexOf("login")>=0 || "/".equals(request.getRequestURI())) {
            return true;
        }
        // 如果没有获取到员工信息,就跳转至登录页面
        response.sendRedirect("/login");
        //  PrintWriter writer = response.getWriter();
        return false;
    }
}
