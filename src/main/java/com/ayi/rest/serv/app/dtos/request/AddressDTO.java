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
        value = "AddressDTO",
        description = "Represents the data needed to create or update address."
)
public class AddressDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "Non negative value, Street is required.")
    @NotNull
    private String street;

    @ApiModelProperty(position = 2, notes = "Non negative value, StreetNumber is required.")
    @NotNull
    private Integer streetNumber;

    @ApiModelProperty(position = 3, notes = "Non negative value, Apartment is not required.")
    private String apartment;

    @ApiModelProperty(position = 4, notes = "Non negative value, Postcode is required.")
    @NotNull
    private String postcode;

    @ApiModelProperty(position = 5, notes = "Non negative value, City is required.")
    @NotNull
    private String city;

    @ApiModelProperty(position = 6, notes = "Non negative value, Province is required.")
    @NotNull
    private String province;

    @ApiModelProperty(position = 7, notes = "Non negative value, Country is required.")
    @NotNull
    private String country;

}
