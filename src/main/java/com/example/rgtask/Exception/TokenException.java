package com.example.rgtask.Exception;

public class TokenException extends RuntimeException{
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
    public TokenException(Integer errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
