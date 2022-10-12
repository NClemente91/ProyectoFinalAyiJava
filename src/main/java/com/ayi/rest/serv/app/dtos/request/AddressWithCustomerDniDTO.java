package com.ayi.rest.serv.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(
        value = "AddressWithCustomerDniDTO",
        description = "Represents the data needed to create or update address."
)
public class AddressWithCustomerDniDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "Street is required.")
    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be empty")
    private String street;

    @ApiModelProperty(position = 2, notes = "StreetNumber is required.")
    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be empty")
    private String streetNumber;

    @ApiModelProperty(position = 3, notes = "Apartment is required.")
    @NotNull(message = "Cannot be null")
    private String apartment;

    @ApiModelProperty(position = 4, notes = "Postcode is required.")
    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be empty")
    private String postcode;

    @ApiModelProperty(position = 5, notes = "City is required.")
    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be empty")
    private String city;

    @ApiModelProperty(position = 6, notes = "Province is required.")
    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be empty")
    private String province;

    @ApiModelProperty(position = 7, notes = "Country is required.")
    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be empty")
    private String country;

    @ApiModelProperty(position = 8, notes = "Customer Dni is required.")
    @NotNull(message = "Cannot be null")
    @NotBlank(message = "Cannot be empty")
    @Pattern(regexp="^[0-9]{7,8}$",message="Length must be 7 or 8 characters")
    private String customerDni;

}
