package com.max.fallinlove.base.exception;

import lombok.Getter;

/**
 * 统一业务异常
 *
 * @author Rick
 * @date 2022-02-08 11:15
 */
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected String errorCode;

    protected String errorMessage;

    public BusinessException(){
        this("业务异常，请重新确认流程。");
    }
    public BusinessException(String errorMessage) {
        this("0000", errorMessage);
    }
    public BusinessException (String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
