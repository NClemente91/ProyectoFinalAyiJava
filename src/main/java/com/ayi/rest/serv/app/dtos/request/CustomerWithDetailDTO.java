package com.ayi.rest.serv.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(
        value = "CustomerWithDetailDTO",
        description = "Represents the data needed to create or update customers with details."
)
public class CustomerWithDetailDTO {

    @ApiModelProperty(position = 1, notes = "Non negative value, CustomerDTO is required.")
    @NotNull
    @NotEmpty
    private CustomerDTO customer;

    @ApiModelProperty(position = 2, notes = "Non negative value, CustomerDetailDTO is required.")
    @NotNull
    @NotEmpty
    private CustomerDetailDTO detail;

}
