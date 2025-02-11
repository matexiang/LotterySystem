package com.lxy.lotterysystem.common.exception;

import com.lxy.lotterysystem.common.errorcode.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data//帮我们是生成equals和hashcode
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    /**
     * @see com.lxy.lotterysystem.common.errorcode.ControllerErrorCodeConstants
     */
    private Integer code;

    private String message;

    public ServiceException() {

    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(ErrorCode errorCode) {

        this.code =errorCode.getCode();
        this.message = errorCode.getMsg();
    }
}
