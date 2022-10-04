package com.ayi.rest.serv.app.dtos.response;

import com.ayi.rest.serv.app.entities.CustomerDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class CustomerWithDetailResponseDTO {

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

    @ApiModelProperty(position = 7, notes = "Non negative value, CustomerDetailDTO is required.")
    private CustomerDetailResponseDTO detail;

}
