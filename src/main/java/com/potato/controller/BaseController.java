package com.potato.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author potato
 * @PackageName:com.potato.controller
 * @Description: TODO
 */
@Controller
public class BaseController {

    /**
     * 多个请求不会共享一个Request对象,因为SpringMVC内部使用了ThreadLocal
     */
    @Autowired
    HttpServletRequest request;
}
