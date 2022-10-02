package com.ayi.rest.serv.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(
        value = "CustomerDetailResponseDTO",
        description = "Represents the data returned for customers details"
)
public class CustomerDetailResponseDTO implements Serializable {

    private Long customerDetailId;

    @ApiModelProperty(position = 1, notes = "Non negative value, IsPrime is required.")
    private Boolean isPrime;

    @ApiModelProperty(position = 2, notes = "Non negative value, Score is required.")
    private Integer score;

    @ApiModelProperty(position = 3, notes = "Non negative value, CreatedAt is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @ApiModelProperty(position = 4, notes = "Non negative value, UpdatedAt is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;
}
