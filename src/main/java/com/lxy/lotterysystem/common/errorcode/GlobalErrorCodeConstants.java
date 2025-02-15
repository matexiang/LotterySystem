package com.lxy.lotterysystem.common.errorcode;

public interface GlobalErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(200,"成功");

    ErrorCode INIERNAL_SERVER_ERROR = new ErrorCode(500,"系统异常");

    ErrorCode UNKNOWN = new ErrorCode(999,"未知错误");


}
