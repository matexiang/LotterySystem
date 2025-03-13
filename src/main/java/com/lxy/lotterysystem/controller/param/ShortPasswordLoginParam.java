package com.lxy.lotterysystem.controller.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShortPasswordLoginParam extends UserLoginParam{

    //电话
    @NotBlank(message = "手机不能为空!")
    private String loginMobile;

    //验证码
    @NotBlank(message = "验证码不能为空!")
    private String verificationCode;
}
