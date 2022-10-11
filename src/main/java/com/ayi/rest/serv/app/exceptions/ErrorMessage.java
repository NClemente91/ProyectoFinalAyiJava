package com.ayi.rest.serv.app.exceptions;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(
        value = "ErrorResponseDTO",
        description = "Represents the data returned in case of errors"
)
public class ErrorMessage {

    @ApiModelProperty(position = 1, notes = "Non negative value, Exception is required.")
    private  String exception;

    @ApiModelProperty(position = 2, notes = "Non negative value, Message is required.")
    private String message;

    @ApiModelProperty(position = 3, notes = "Non negative value, Path is required.")
    private String path;
}
