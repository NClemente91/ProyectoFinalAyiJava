package com.ayi.rest.serv.app.dtos.request.mappers.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDetailDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.entities.CustomerDetail;
import com.ayi.rest.serv.app.dtos.request.mappers.ICustomerDetailMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerDetailMapperImpl implements ICustomerDetailMapper {

    private final ModelMapper modelMapper;

    @Override
    public CustomerDetailResponseDTO entityToResponseDto(CustomerDetail entity) {

        CustomerDetailResponseDTO customerDetailResponseDTO = new CustomerDetailResponseDTO();
        modelMapper.map(entity, customerDetailResponseDTO);
        return customerDetailResponseDTO;

    }

    @Override
    public CustomerDetail requestDtoToEntity(CustomerDetailDTO requestDto) {

        CustomerDetail customerDetailEntity = new CustomerDetail();
        modelMapper.map(requestDto, customerDetailEntity);
        return customerDetailEntity;

    }

}
