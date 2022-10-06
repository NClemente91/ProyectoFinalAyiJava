package com.ayi.rest.serv.app.mappers.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.FullCustomerResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.entities.CustomerDetail;
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

    @Override
    public FullCustomerResponseDTO entityToFullResponseDto(Customer customer) {

        CustomerDetailResponseDTO customerDetailResponseDTO = new CustomerDetailResponseDTO();
        modelMapper.map(customer.getCustomerDetail(), customerDetailResponseDTO);

        List<AddressResponseDTO> addressResponseDTOList = new ArrayList<>();
        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();

        for(Address address:customer.getAddressList()){
            modelMapper.map(address, addressResponseDTO);
            addressResponseDTOList.add(addressResponseDTO);
        }

        return FullCustomerResponseDTO.builder()
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
