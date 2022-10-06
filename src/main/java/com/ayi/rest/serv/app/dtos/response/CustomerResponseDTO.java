package com.ayi.rest.serv.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(
        value = "CustomerResponseDTO",
        description = "Represents the data returned for customers"
)
public class CustomerResponseDTO implements Serializable {

    private Long customerId;

    @ApiModelProperty(position = 1, notes = "Non negative value, Name is required.")
    private String name;

    @ApiModelProperty(position = 2, notes = "Non negative value, LastName is required.")
    private String lastName;

    @ApiModelProperty(position = 3, notes = "Non negative value, DNI is required.")
    private String dni;

    @ApiModelProperty(position = 4, notes = "Non negative value, DateOfBrith is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @ApiModelProperty(position = 5, notes = "Non negative value, CreatedAt is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @ApiModelProperty(position = 6, notes = "Non negative value, UpdatedAt is not required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

    @ApiModelProperty(position = 7, notes = "Non null value, CustomerDetailResponseDTO is required.")
    private CustomerDetailResponseDTO detail;

    @ApiModelProperty(position = 8, notes = "Non null value, AddressResponseDTO is required.")
    private List<AddressResponseDTO> addresses;

}
