package com.ayi.rest.serv.app.mappers.impl;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.mappers.IAddressMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapperImpl implements IAddressMapper {

    private final ModelMapper modelMapper;

    @Override
    public AddressResponseDTO entityToResponseDto(Address entity) {

        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
        modelMapper.map(entity, addressResponseDTO);
        return addressResponseDTO;

    }

    @Override
    public Address requestDtoToEntity(AddressDTO requestDto) {

        Address addressEntity = new Address();
        modelMapper.map(requestDto, addressEntity);
        return addressEntity;

    }

}
