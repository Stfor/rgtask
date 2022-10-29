package com.example.rgtask.Exception;

import io.swagger.models.auth.In;

/**
 * 自定义service层异常类 RuntimeException 可以不捕获也没事
 */
public class TokenOverdue extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private Integer errorCode;
    private String message;

    public Integer getResultCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setResultCode(Integer resultCode) {
        this.errorCode = resultCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public TokenOverdue(Integer errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
