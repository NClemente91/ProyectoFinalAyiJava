package com.ayi.rest.serv.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(
        value = "FullInvoiceResponseDTO",
        description = "Represents the data returned for full invoices"
)
public class FullInvoiceResponseDTO implements Serializable {

    private Long invoiceId;

    @ApiModelProperty(position = 1, notes = "Non null value, InvoiceType is required.")
    private String invoiceType;

    @ApiModelProperty(position = 2, notes = "Non null value, Description is required.")
    private String description;

    @ApiModelProperty(position = 3, notes = "Non null value, Total is required.")
    private Double total;

    @ApiModelProperty(position = 4, notes = "Non null value, CreatedAt is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @ApiModelProperty(position = 5, notes = "Non null value, UpdatedAt is not required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

    @ApiModelProperty(position = 6, notes = "Non null value, Total is required.")
    private CustomerResponseDTO customer;

}
