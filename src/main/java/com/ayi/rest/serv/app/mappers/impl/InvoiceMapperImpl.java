package com.ayi.rest.serv.app.mappers.impl;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;
import com.ayi.rest.serv.app.entities.Invoice;
import com.ayi.rest.serv.app.mappers.IInvoiceMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceMapperImpl extends Exception implements IInvoiceMapper {

    private final ModelMapper modelMapper;

    @Override
    public InvoiceResponseDTO entityToResponseDto(Invoice entity) {

        InvoiceResponseDTO invoiceResponseDTO = new InvoiceResponseDTO();
        modelMapper.map(entity, invoiceResponseDTO);
        return invoiceResponseDTO;

    }

    @Override
    public Invoice requestDtoToEntity(InvoiceDTO requestDto) {

        Invoice invoiceEntity = new Invoice();
        modelMapper.map(requestDto, invoiceEntity);
        return invoiceEntity;

    }

}
