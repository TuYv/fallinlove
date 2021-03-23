package com.max.fallinlove.common;

import com.max.fallinlove.constants.RequestConstants;
import java.util.Date;
import java.util.UUID;

public class ResultUtils {

    private ResultUtils() {
    }

    public static <T> Result<T> success() {
        return Result.<T>builder()
            .returnStatus(RequestConstants.RETURN_STATUS_SUCCESS)
            .serverTime(createServerTime())
            .requestId(createRequestId())
            .build();
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
            .returnStatus(RequestConstants.RETURN_STATUS_SUCCESS)
            .data(data)
            .serverTime(createServerTime())
            .requestId(createRequestId())
            .build();
    }

    public static <T> Result<T> fail(String errorCode, String errorMessage, Object extMessage) {
        return Result.<T>builder()
            .returnStatus(RequestConstants.RETURN_STATUS_FAILED)
            .errorCode(errorCode)
            .errorMessage(errorMessage)
            .serverTime(createServerTime())
            .requestId(createRequestId())
            .extMessage(extMessage)
            .build();
    }

    public static <T> Result<T> fail(String errorCode, String errorMessage) {
        return Result.<T>builder()
            .returnStatus(RequestConstants.RETURN_STATUS_FAILED)
            .errorCode(errorCode)
            .errorMessage(errorMessage)
            .serverTime(createServerTime())
            .requestId(createRequestId())
            .build();
    }


    private static String createServerTime() {
        return String.valueOf(new Date().getTime());
    }

    private static String createRequestId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static boolean resultSuccess(Result result) {
        return RequestConstants.RETURN_STATUS_SUCCESS.equals(result.getReturnStatus());
    }

    public static boolean resultFailed(Result result) {
        return !resultSuccess(result);
    }
}
