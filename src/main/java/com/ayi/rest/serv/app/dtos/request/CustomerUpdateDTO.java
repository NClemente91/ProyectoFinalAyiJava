package com.ayi.rest.serv.app.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @ApiModelProperty(position = 1, notes = "Non negative value, Name is required.")
    @NotNull
    @NotEmpty
    private String name;

    @ApiModelProperty(position = 2, notes = "Non negative value, LastName is required.")
    @NotNull
    @NotEmpty
    private String lastName;

    @ApiModelProperty(position = 3, notes = "Non negative value, DNI is required.")
    @NotNull
    @NotEmpty
    private String dni;

    @ApiModelProperty(position = 4, notes = "Non negative value, DateOfBrith is required.")
    @NotNull
    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

}
