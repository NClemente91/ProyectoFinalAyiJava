package com.ayi.rest.serv.app.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(
        value = "CustomerUpdateDTO",
        description = "Represents the data needed to create or update customers."
)
public class CustomerUpdateDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "Name is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String name;

    @ApiModelProperty(position = 2, notes = "LastName is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String lastName;

    @ApiModelProperty(position = 3, notes = "DNI is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    @Pattern(regexp="^[0-9]{7,8}$",message="length must be 7 or 8 characters")
    private String dni;

    @ApiModelProperty(position = 4, notes = "DateOfBrith is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    @Past(message = "Must be a past date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

}
