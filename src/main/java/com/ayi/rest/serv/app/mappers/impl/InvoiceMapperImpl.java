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
    public Invoice requestDtoToEntity(InvoiceDTO requestDto) {

        return modelMapper.map(requestDto, Invoice.class);

    }

    @Override
    public InvoiceResponseDTO entityToResponseDto(Invoice invoice) {

        CustomerResponseDTO fullCustomerResponseDTO;

        fullCustomerResponseDTO = customerMapper.entityToResponseDto(invoice.getCustomer());

        return InvoiceResponseDTO.builder()
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
