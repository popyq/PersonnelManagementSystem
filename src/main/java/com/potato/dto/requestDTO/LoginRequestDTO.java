package com.potato.dto.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author potato
 * @PackageName:com.potato.dto.requestDTO
 * @Description: 用户登录RequestDTO
 */

@Data
@ApiModel(value = "登录",description = "用户登录DTO")
public class LoginRequestDTO implements Serializable {

    @ApiModelProperty(value = "用户名", required = true,example = "111111")
    private String userName;

    @ApiModelProperty(value = "密码", required = true,example = "111111")
    private String password;

    @ApiModelProperty(value = "是否要对用户输入的验证码进行效验",required = false,example = "false")
    private Boolean needVerify;

    @ApiModelProperty(value = "用户输入的验证码",required = false,example = "1234")
    private String verifyCodeActual;

}
