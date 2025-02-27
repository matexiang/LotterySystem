package com.lxy.lotterysystem.common.errorcode;

import lombok.Data;

@Data
public class ErrorCode {

    private final Integer code;

    private  String msg;

    public ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
