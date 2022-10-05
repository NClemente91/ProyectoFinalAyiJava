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
        value = "FullInvoiceDTO",
        description = "Represents the data needed to create invoices with clients."
)
public class FullInvoiceDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "Non null value, Invoice is required.")
    @NotNull
    private InvoiceDTO invoice;

    @ApiModelProperty(position = 2, notes = "Non null value, Total is required.")
    @NotNull
    private String customerDni;

}
