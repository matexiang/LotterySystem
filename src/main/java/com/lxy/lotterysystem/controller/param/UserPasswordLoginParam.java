package com.lxy.lotterysystem.controller.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPasswordLoginParam extends UserLoginParam{

    /**
     * 手机或邮箱
     */
    @NotBlank(message = "手机邮箱不能为空!")
    private String loginName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空!")
    private String password;

}
