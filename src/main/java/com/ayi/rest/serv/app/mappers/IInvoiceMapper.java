package com.ayi.rest.serv.app.mappers;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.request.InvoiceUpdateDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceWithoutCustomerResponseDTO;
import com.ayi.rest.serv.app.entities.Invoice;

public interface IInvoiceMapper {
    Invoice requestDtoToEntity(InvoiceDTO requestDto);

    Invoice requestDtoToEntity(InvoiceUpdateDTO requestDto);

    abstract InvoiceResponseDTO entityToResponseDto(Invoice invoice);

    InvoiceWithoutCustomerResponseDTO entityToInvoiceWithoutCustomerResponseDto(Invoice invoice);
}
