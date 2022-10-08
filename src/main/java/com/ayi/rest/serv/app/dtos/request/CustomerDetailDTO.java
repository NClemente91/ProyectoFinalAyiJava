package com.ayi.rest.serv.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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

    @ApiModelProperty(position = 1, notes = "Non null value, IsPrime is required.")
    @NotNull
    private Boolean isPrime;

    @ApiModelProperty(position = 2, notes = "Non null value, Score is required.")
    @NotNull
    private Integer score;

}
