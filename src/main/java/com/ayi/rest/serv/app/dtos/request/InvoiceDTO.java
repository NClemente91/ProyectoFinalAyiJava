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
        value = "InvoiceDTO",
        description = "Represents the data needed to create or update invoices."
)
public class InvoiceDTO implements Serializable {

    @ApiModelProperty(position = 1, notes = "Non negative value, InvoiceType is required.")
    @NotNull
    private String invoiceType;

    @ApiModelProperty(position = 2, notes = "Non negative value, Description is required.")
    @NotNull
    private String description;

    @ApiModelProperty(position = 3, notes = "Non negative value, Total is required.")
    @NotNull
    private Double total;

    @ApiModelProperty(position = 4, notes = "Non null value, Customer Dni is required.")
    @NotNull
    private String customerDni;

}
