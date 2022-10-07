package com.ayi.rest.serv.app.dtos.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
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

    @ApiModelProperty(position = 1, notes = "Non negative value, InvoiceType is required.")
    @NotNull
    private String invoiceType;

    @ApiModelProperty(position = 2, notes = "Non negative value, Description is required.")
    @NotNull
    private String description;

    @ApiModelProperty(position = 3, notes = "Non negative value, Total is required.")
    @NotNull
    private Double total;

}
