package com.ayi.rest.serv.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
        value = "CustomerWithDetailResponseDTO",
        description = "Represents the data returned to create or update customers with details."
)
public class FullCustomerResponseDTO {

    private Long customerId;

    @ApiModelProperty(position = 1, notes = "Non null value, Name is required.")
    private String name;

    @ApiModelProperty(position = 2, notes = "Non null value, LastName is required.")
    private String lastName;

    @ApiModelProperty(position = 3, notes = "Non null value, DNI is required.")
    private String dni;

    @ApiModelProperty(position = 4, notes = "Non null value, DateOfBrith is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @ApiModelProperty(position = 5, notes = "Non null value, CreatedAt is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @ApiModelProperty(position = 6, notes = "Non null value, UpdatedAt is not required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

    @ApiModelProperty(position = 7, notes = "Non null value, CustomerDetailResponseDTO is required.")
    private CustomerDetailResponseDTO detail;

    @ApiModelProperty(position = 8, notes = "Non null value, AddressResponseDTO is required.")
    private List<AddressResponseDTO> addresses;

}
