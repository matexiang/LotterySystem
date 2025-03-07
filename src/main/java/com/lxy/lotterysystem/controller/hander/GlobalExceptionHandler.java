package com.lxy.lotterysystem.controller.hander;

import com.lxy.lotterysystem.common.CommonResult;
import com.lxy.lotterysystem.common.errorcode.GlobalErrorCodeConstants;
import com.lxy.lotterysystem.common.exception.ControllerException;
import com.lxy.lotterysystem.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;

@RestControllerAdvice //可以捕获全局抛的异常
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ServiceException.class)
    public CommonResult<?> serviceException(ServiceException e){
        logger.error("serviceException:", e);

        return CommonResult.error(GlobalErrorCodeConstants.INIERNAL_SERVER_ERROR.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ControllerException.class)
    public CommonResult<?> controllerException(ControllerException e){
        logger.error("controllerException:", e);

        return CommonResult.error(GlobalErrorCodeConstants.INIERNAL_SERVER_ERROR.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public CommonResult<?> Exception(Exception e){
        logger.error("服务异常:", e);

        return CommonResult.error(GlobalErrorCodeConstants.INIERNAL_SERVER_ERROR.getCode(),e.getMessage());
    }


}
