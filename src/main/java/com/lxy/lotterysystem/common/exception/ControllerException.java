package com.lxy.lotterysystem.common.exception;

import com.lxy.lotterysystem.common.errorcode.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ControllerException extends RuntimeException{

    /**
     * @see com.lxy.lotterysystem.common.errorcode.ControllerErrorCodeConstants
     */
    private Integer code;

    private String message;

    public ControllerException(){

    }

    public ControllerException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ControllerException(ErrorCode errorCode) {

        this.code =errorCode.getCode();
        this.message = errorCode.getMsg();
    }


}
