package com.ayi.rest.serv.app.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    @ApiModelProperty(position = 1, notes = "Non negative value, IsPrime is required.")
    @NotNull
    private Boolean isPrime;

    @ApiModelProperty(position = 2, notes = "Non negative value, Score is required.")
    @NotNull
    private Integer score;

    @ApiModelProperty(position = 3, notes = "Non negative value, CreatedAt is required.")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @ApiModelProperty(position = 4, notes = "Non negative value, UpdatedAt is required.")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

}
