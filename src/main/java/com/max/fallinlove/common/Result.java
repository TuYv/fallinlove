package com.max.fallinlove.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public Result success(Integer code, String msg, T data) {
        return new Result(code, msg, data);
    }

    public Result success(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    public Result success() {
        return success(200, "请求成功");
    }

    public Result success(T data) {
        return success(200, "请求成功", data);
    }

    public Result failed(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    public Result failed() {
        return new Result(500, "请求出现未知错误", null);
    }

}
