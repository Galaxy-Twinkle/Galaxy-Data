package com.galaxy.data.common;

import com.galaxy.data.common.enums.ErrorCodeMessagesEnum;

/**
 * @author yao.qian
 */
public class BaseException extends RuntimeException{

    /**
     * 统一错误码
     */
    private Integer code;

    /**
     * 系统错误信息
     */
    private String message;

    public BaseException() {
    }

    public BaseException(Integer code , String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(ErrorCodeMessagesEnum messagesEnum) {
        this.code = messagesEnum.getCode();
        this.message = messagesEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
