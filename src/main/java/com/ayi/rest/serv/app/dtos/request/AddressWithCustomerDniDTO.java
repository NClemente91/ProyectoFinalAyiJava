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
        value = "AddressWithCustomerDniDTO",
        description = "Represents the data needed to create or update address."
)
public class AddressWithCustomerDniDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "Non negative value, Street is required.")
    @NotNull
    @NotEmpty
    private String street;

    @ApiModelProperty(position = 2, notes = "Non negative value, StreetNumber is required.")
    @NotNull
    @NotEmpty
    private String streetNumber;

    @ApiModelProperty(position = 3, notes = "Non negative value, Apartment is not required.")
    @NotNull
    @NotEmpty
    private String apartment;

    @ApiModelProperty(position = 4, notes = "Non negative value, Postcode is required.")
    @NotNull
    @NotEmpty
    private String postcode;

    @ApiModelProperty(position = 5, notes = "Non negative value, City is required.")
    @NotNull
    @NotEmpty
    private String city;

    @ApiModelProperty(position = 6, notes = "Non negative value, Province is required.")
    @NotNull
    @NotEmpty
    private String province;

    @ApiModelProperty(position = 7, notes = "Non negative value, Country is required.")
    @NotNull
    @NotEmpty
    private String country;

    @ApiModelProperty(position = 8, notes = "Non null value, Customer Dni is required.")
    @NotNull
    private String customerDni;

}