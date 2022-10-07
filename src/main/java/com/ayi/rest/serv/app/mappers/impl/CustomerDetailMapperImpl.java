package com.ayi.rest.serv.app.mappers.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDetailDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.entities.CustomerDetail;
import com.ayi.rest.serv.app.mappers.ICustomerDetailMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerDetailMapperImpl implements ICustomerDetailMapper {

    private final ModelMapper modelMapper;

    @Override
    public CustomerDetailResponseDTO entityToResponseDto(CustomerDetail entity) {

        return modelMapper.map(entity, CustomerDetailResponseDTO.class);

    }

    @Override
    public CustomerDetail requestDtoToEntity(CustomerDetailDTO requestDto) {

        return modelMapper.map(requestDto, CustomerDetail.class);

    }

}
