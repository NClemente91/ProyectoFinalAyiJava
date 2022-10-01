package com.ayi.rest.serv.app.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDTO {

    private  String exception;
    private Integer statusCode;
    private String message;
    private String path;

}
