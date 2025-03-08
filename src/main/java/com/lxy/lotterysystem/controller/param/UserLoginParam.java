package com.lxy.lotterysystem.controller.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginParam implements Serializable {

    /**
     * 强制某身份登录,不填不限制身份
     * @see com.lxy.lotterysystem.service.enums.UserIdentityEnum
     */
     private String mandatoryIdentity;



}
