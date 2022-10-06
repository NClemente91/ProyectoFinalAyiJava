package com.ayi.rest.serv.app.mappers.impl;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.response.*;
import com.ayi.rest.serv.app.entities.Invoice;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
import com.ayi.rest.serv.app.mappers.IInvoiceMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceMapperImpl implements IInvoiceMapper {

    private final ModelMapper modelMapper;

    @Autowired
    private ICustomerMapper customerMapper;

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
    public FullInvoiceResponseDTO entityToFullResponseDto(Invoice invoice) {

        FullCustomerResponseDTO fullCustomerResponseDTO;

        fullCustomerResponseDTO = customerMapper.entityToFullResponseDto(invoice.getCustomer());

        return FullInvoiceResponseDTO.builder()
                .invoiceId(invoice.getInvoiceId())
                .invoiceType(invoice.getInvoiceType())
                .description(invoice.getDescription())
                .total(invoice.getTotal())
                .createdAt(invoice.getCreatedAt())
                .updatedAt(invoice.getUpdatedAt())
                .customer(fullCustomerResponseDTO)
                .build();

    }

}
