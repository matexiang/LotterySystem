package com.lxy.lotterysystem.common.errorcode;

public interface ServiceErrorCodeConstants {

    //人员模块错误码
    ErrorCode REGISTER_INFO_EMPTY = new ErrorCode(100,"注册信息为空");

    ErrorCode MAIL_ERROR= new ErrorCode(101,"邮箱错误");

    ErrorCode PHONE_NUMBER_ERROR = new ErrorCode(102,"手机号错误");

    ErrorCode IDENTITY_ERROR = new ErrorCode(103,"身份错误");

    ErrorCode PASSWORD_IS_EMPTY = new ErrorCode(104,"密码不能为空");

    ErrorCode PASSWORD_ERROR = new ErrorCode(105,"密码错误");

    ErrorCode MAIL_USED = new ErrorCode(106,"邮箱被使用");

    ErrorCode PHONE_NUMBER_USED = new ErrorCode(107,"手机号被使用");


    ErrorCode LOGIN_INFO_NOT_EXIT = new ErrorCode(108,"登录信息不存在");

    ErrorCode LOGIN_NOT_EXIT = new ErrorCode(109,"登录方式不存在");

    ErrorCode USER_INFO_IS_EMPTY = new ErrorCode(110,"用户信息不存在");

    ErrorCode VERIFICATION_CODE_ERROR = new ErrorCode(111,"验证码校验失败");








    //活动模块错误码
    ErrorCode CREATE_ACTIVITY_INFO_IS_EMPTY = new ErrorCode(300,"创建的活动信息为空!");
    ErrorCode ACTIVITY_USER_ERROR = new ErrorCode(301,"创建的活动关联人员异常!");
    ErrorCode ACTIVITY_PRIZE_ERROR = new ErrorCode(302,"创建的活动关联奖品异常!");
    ErrorCode USER_PRIZE_AMOUNT_ERROR = new ErrorCode(303,"创建的活动关联奖品及人员数量异常!");
    ErrorCode ACTIVITY_PRIZE_TIERS_ERROR = new ErrorCode(304,"创建的活动关联奖品等级设置错误!");

    ErrorCode ACTIVITY_STATUS_CONVERT_ERROR = new ErrorCode(305,"活动先关状态转换失败!");
    ErrorCode CHCHE_ACTIVITY_ID_IS_EMPTY = new ErrorCode(306,"缓存活动的活动id为空!");
    ErrorCode CHCHE_ACTIVITY_ID_ERROR = new ErrorCode(307,"要缓存的活动id有误!");

    //奖品模块错误码


    //抽奖模块错误码
    ErrorCode ACTIVITY_OR_PRIZE_IS_EMPTY = new ErrorCode(400,"活动或关联奖品不存在!");

    ErrorCode ACTIVITY_COMPLETED = new ErrorCode(401,"抽奖活动已完成无法抽奖!");

    ErrorCode ACTIVITY_PRIZE_COMPLETED = new ErrorCode(402,"当前奖品已被抽取无法抽奖!");
    ErrorCode WINNER_PRIZE_AMOUNT_ERROR = new ErrorCode(403,"中奖人数与奖品数量不一致,无法抽奖!");

    //图片模块错误码
    ErrorCode PIC_UPLOAD_ERROR = new ErrorCode(500,"图片上传失败");



}
