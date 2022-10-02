package com.ayi.rest.serv.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel(
        value = "ErrorResponseDTO",
        description = "Represents the data returned in case of errors"
)
public class ErrorResponseDTO {

    @ApiModelProperty(position = 1, notes = "Non negative value, Exception is required.")
    @NotNull
    private  String exception;

    @ApiModelProperty(position = 2, notes = "Non negative value, Message is required.")
    @NotNull
    private String message;

    @ApiModelProperty(position = 3, notes = "Non negative value, Path is required.")
    @NotNull
    private String path;
}
