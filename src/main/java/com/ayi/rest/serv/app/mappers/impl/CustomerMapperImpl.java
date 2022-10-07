package com.ayi.rest.serv.app.mappers.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.request.CustomerUpdateDTO;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.entities.Customer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomerMapperImpl implements ICustomerMapper {

    private final ModelMapper modelMapper;

    @Override
    public Customer requestDtoToEntity(CustomerDTO requestDto) {

        return modelMapper.map(requestDto, Customer.class);

    }

    @Override
    public Customer requestDtoToEntity(CustomerUpdateDTO requestDto) {

        return modelMapper.map(requestDto, Customer.class);

    }

    @Override
    public CustomerResponseDTO entityToResponseDto(Customer customer) {

        CustomerDetailResponseDTO customerDetailResponseDTO = new CustomerDetailResponseDTO();
        modelMapper.map(customer.getCustomerDetail(), customerDetailResponseDTO);

        List<AddressResponseDTO> addressResponseDTOList = new ArrayList<>();

        for(Address address:customer.getAddressList()){
            addressResponseDTOList.add(modelMapper.map(address, AddressResponseDTO.class));
        }

        return CustomerResponseDTO.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .dni(customer.getDni())
                .dateOfBirth(customer.getDateOfBirth())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .detail(customerDetailResponseDTO)
                .addresses(addressResponseDTOList)
                .build();
    }

}
