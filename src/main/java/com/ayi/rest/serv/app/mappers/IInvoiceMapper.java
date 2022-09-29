package com.ayi.rest.serv.app.mappers;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;
import com.ayi.rest.serv.app.entities.Invoice;

public interface IInvoiceMapper {
    InvoiceResponseDTO entityToResponseDto(Invoice entity);
    Invoice requestDtoToEntity(InvoiceDTO requestDto);
}
