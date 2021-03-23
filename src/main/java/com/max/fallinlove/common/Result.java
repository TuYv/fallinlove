package com.max.fallinlove.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

/**
 * @author max
 */

@Data
@Builder(toBuilder = true)
@ApiModel(value = "API统一返回数据封装类")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result<T> {

    @ApiModelProperty(value = "返回状态:SUCCEED和FAILED")
    private String returnStatus;

    @ApiModelProperty(value = "时间戳")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String serverTime;

    @ApiModelProperty(value = "请求ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestId;

    @ApiModelProperty(value = "错误码")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;

    @ApiModelProperty(value = "错误信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorMessage;

    @ApiModelProperty(value = "错误扩展信息")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object extMessage;

    @ApiModelProperty(value = "总共条数")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalCount;

    @ApiModelProperty(value = "页数")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageNo;

    @ApiModelProperty(value = "本页条数")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize;

    @ApiModelProperty(value = "封装数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @Tolerate
    public Result(){}
}
