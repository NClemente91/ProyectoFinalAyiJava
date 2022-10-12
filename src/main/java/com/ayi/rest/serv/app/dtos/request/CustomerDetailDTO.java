package com.ayi.rest.serv.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(
        value = "CustomerDetailDTO",
        description = "Represents the data needed to create or update customers details."
)
public class CustomerDetailDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "IsPrime is required.")
    @NotNull(message = "Cannot be null")
    private Boolean isPrime;

    @ApiModelProperty(position = 2, notes = "Score is required.")
    @NotNull(message = "Cannot be null")
    private Integer score;

}
