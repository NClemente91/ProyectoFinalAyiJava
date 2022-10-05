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
public class FullCustomerDTO {

    @ApiModelProperty(position = 1, notes = "Non null value, CustomerDTO is required.")
    @NotNull
    @NotEmpty
    private CustomerDTO customer;

    @ApiModelProperty(position = 2, notes = "Non null value, CustomerDetailDTO is required.")
    @NotNull
    @NotEmpty
    private CustomerDetailDTO detail;

    @ApiModelProperty(position = 3, notes = "Non null value, AddressDTO is required.")
    @NotNull
    @NotEmpty
    private AddressDTO address;

}
