package com.potato.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author potato
 * @PackageName:com.potato.util
 * @Description: 对验证码进行验证
 */

public class CodeUtil {

    /**
     * 进行验证
     * @param request  通过request获得实际生成的验证码
     * @param verifyCodeActual  用户输入的验证码
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request,String verifyCodeActual){
        // 正确的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        // 用户输入的验证码
        verifyCodeActual= verifyCodeActual.toUpperCase();
        if (verifyCodeActual == null || !verifyCodeExpected.equals(verifyCodeActual)){
            return false;
        }
        return true;
    }

}
