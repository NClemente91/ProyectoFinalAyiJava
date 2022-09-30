package com.ayi.rest.serv.app.mappers.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapperImpl implements ICustomerMapper {

    private final ModelMapper modelMapper;

    @Override
    public CustomerResponseDTO entityToResponseDto(Customer entity) {

        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
        modelMapper.map(entity, customerResponseDTO);
        return customerResponseDTO;

    }

    @Override
    public Customer requestDtoToEntity(CustomerDTO requestDto) {

        Customer customerEntity = new Customer();
        modelMapper.map(requestDto, customerEntity);
        return customerEntity;

    }

}
