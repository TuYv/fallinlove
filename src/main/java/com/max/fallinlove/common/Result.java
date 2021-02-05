package com.max.fallinlove.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author max
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public Result<T> success(Integer code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    public Result<T> success(Integer code, String msg) {
        return new Result<T>(code, msg, null);
    }

    public Result<T> success() {
        return success(200, "请求成功");
    }

    public Result<T> success(T data) {
        return success(200, "请求成功", data);
    }

    public Result<T> failed(Integer code, String msg) {
        return new Result<T>(code, msg, null);
    }

    public Result<T> failed() {
        return new Result<T>(500, "请求出现未知错误", null);
    }

}
