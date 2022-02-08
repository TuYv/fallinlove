package com.max.fallinlove.common.advice;

import com.max.fallinlove.base.exception.BusinessException;
import com.max.fallinlove.common.result.Result;
import com.max.fallinlove.common.result.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Rick
 * @date 2022-02-08 11:24
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Result businessExceptionHandler(BusinessException e) {
        return ResultUtils.fail(e.getErrorCode(), e.getErrorMessage());
    }
}
