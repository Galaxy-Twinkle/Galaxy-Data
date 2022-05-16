package com.galaxy.data.common;

import com.galaxy.data.common.enums.ErrorCodeMessagesEnum;

import java.util.List;

/**
 * 公共返回类
 *
 * @author yao.qian
 */
public class Result<T> {

    /**
     * 统一错误码
     */
    protected Integer code;

    /**
     * 系统错误信息
     */
    protected String message;

    /**
     * 数据集合
     */
    protected List<T> data;

    public Integer getCode() {
        return code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public List<T> getData() {
        return data;
    }

    public Result<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public Result<T> failed(BaseException e) {
        this.message = e.getMessage();
        this.code = e.getCode();
        return this;
    }

    /**
     * 操作失败
     *
     * @return 返回结果
     */
    public static Result failed() {
        return new Result<>().setCode(ErrorCodeMessagesEnum.OPERATION_FAILED.getCode())
                .setMessage(ErrorCodeMessagesEnum.OPERATION_FAILED.getMessage());
    }

    /**
     * 操作成功
     *
     * @return 返回结果
     */
    public static Result success() {
        return new Result<>().setCode(ErrorCodeMessagesEnum.OPERATION_SUCCESSFUL.getCode())
                .setMessage(ErrorCodeMessagesEnum.OPERATION_SUCCESSFUL.getMessage());
    }

    /**
     * 达到预期操作成功,未达到操作失败
     *
     * @param i 影响条数
     * @return 返回结果
     */
    public static Result expect(int i) {
        return i > 0 ? success() : failed();
    }

    public static <T> Result<T> data(List<T> list) {
        return success().setData(list);
    }
}
