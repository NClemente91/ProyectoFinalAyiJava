package com.ayi.rest.serv.app.dtos.request.mappers;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.entities.Address;

public interface IAddressMapper {
    AddressResponseDTO entityToResponseDto(Address entity);
    Address requestDtoToEntity(AddressDTO requestDto);
}
