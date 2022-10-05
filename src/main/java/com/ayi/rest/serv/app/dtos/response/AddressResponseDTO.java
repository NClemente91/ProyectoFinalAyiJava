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
        value = "AddressResponseDTO",
        description = "Represents the data returned for addresses"
)
public class AddressResponseDTO implements Serializable {

    private Long addressId;

    @ApiModelProperty(position = 1, notes = "Non negative value, Street is required.")
    private String street;

    @ApiModelProperty(position = 2, notes = "Non negative value, StreetNumber is required.")
    private String streetNumber;

    @ApiModelProperty(position = 3, notes = "Non negative value, Apartment is not required.")
    private String apartment;

    @ApiModelProperty(position = 4, notes = "Non negative value, Postcode is required.")
    private String postcode;

    @ApiModelProperty(position = 5, notes = "Non negative value, City is required.")
    private String city;

    @ApiModelProperty(position = 6, notes = "Non negative value, Province is required.")
    private String province;

    @ApiModelProperty(position = 7, notes = "Non negative value, Country is required.")
    private String country;

}
