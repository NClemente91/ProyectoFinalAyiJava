package com.ayi.rest.serv.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(
        value = "InvoiceUpdateDTO",
        description = "Represents the data needed to create or update invoices."
)
public class InvoiceUpdateDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "InvoiceType is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    @Pattern(regexp="^[aAbBcC$]",message="Must be: A, B or C")
    private String invoiceType;

    @ApiModelProperty(position = 2, notes = "Description is required.")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private String description;

    @ApiModelProperty(position = 3, notes = "Total is required.")
    @NotNull(message = "Cannot be null")
    private Double total;

}
