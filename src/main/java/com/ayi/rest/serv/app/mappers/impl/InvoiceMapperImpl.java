package com.ayi.rest.serv.app.mappers.impl;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.response.*;
import com.ayi.rest.serv.app.entities.Invoice;
import com.ayi.rest.serv.app.mappers.IInvoiceMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceMapperImpl implements IInvoiceMapper {

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

    @Override
    public FullInvoiceResponseDTO entitiesToFullInvoiceResponseDto(Invoice invoice) {

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();

        modelMapper.map(invoice.getCustomer(), customerResponseDTO);

        return FullInvoiceResponseDTO.builder()
                .invoiceId(invoice.getInvoiceId())
                .invoiceType(invoice.getInvoiceType())
                .description(invoice.getDescription())
                .total(invoice.getTotal())
                .createdAt(invoice.getCreatedAt())
                .updatedAt(invoice.getUpdatedAt())
                .customer(customerResponseDTO)
                .build();

    }

}
