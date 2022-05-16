package com.galaxy.data.common.enums;

/**
 * @author yao.qian
 * 异常信息枚举
 */
public enum ErrorCodeMessagesEnum {

    /**
     * 身份认证失败
     */
    AUTHENTICATION_FAILED(10401, "Authentication failed"),

    /**
     * 身份认证成功
     */
    AUTHENTICATION_SUCCESS(10200, "Authentication success"),

    /**
     * 权限不足
     */
    NO_PERMISSION(10401, "no permission"),

    /**
     * 系统内部错误
     */
    SYSTEM_INTERNAL_ERROR(10500, "system internal error"),

    /**
     * 未找到该用户（用户不存在）
     */
    USER_VERIFIERS_FAILED(10500, "User not found"),

    /**
     * 密码校验失败
     */
    PASSWORD_VERIFIERS_FAILED(10500, "Password verifiers failed"),

    /**
     * 未查询到数据
     */
    DATA_IS_EMPTY(10404, "Data is empty"),


    /**
     * 操作成功
     */
    OPERATION_SUCCESSFUL(10200, "Operation successful"),


    /**
     * 操作失败
     */
    OPERATION_FAILED(10500, "Operation failed");

    /**
     * 统一错误码
     */
    private final Integer code;

    /**
     * 系统错误信息
     */
    private final String message;

    ErrorCodeMessagesEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
