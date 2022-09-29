package com.ayi.rest.serv.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@ApiModel(
        value = "InvoiceResponseDTO",
        description = "Represents the data returned for invoices"
)
public class InvoiceResponseDTO implements Serializable {

    private Long invoiceId;

    @ApiModelProperty(position = 1, notes = "Non negative value, InvoiceType is required.")
    private String invoiceType;

    @ApiModelProperty(position = 2, notes = "Non negative value, Description is required.")
    private String description;

    @ApiModelProperty(position = 3, notes = "Non negative value, Total is required.")
    private Double total;

    @ApiModelProperty(position = 4, notes = "Non negative value, CreatedAt is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @ApiModelProperty(position = 5, notes = "Non negative value, UpdatedAt is not required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

}
