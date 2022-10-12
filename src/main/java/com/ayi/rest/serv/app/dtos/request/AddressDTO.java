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
        value = "AddressDTO",
        description = "Represents the data needed to create or update address."
)
public class AddressDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "Street is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String street;

    @ApiModelProperty(position = 2, notes = "StreetNumber is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String streetNumber;

    @ApiModelProperty(position = 3, notes = "Apartment is required.")
    @NotNull(message = "Cannot be null")
    private String apartment;

    @ApiModelProperty(position = 4, notes = "Postcode is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String postcode;

    @ApiModelProperty(position = 5, notes = "City is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String city;

    @ApiModelProperty(position = 6, notes = "Province is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String province;

    @ApiModelProperty(position = 7, notes = "Country is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String country;

}
