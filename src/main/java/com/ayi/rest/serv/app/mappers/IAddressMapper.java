package com.ayi.rest.serv.app.mappers;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.request.AddressWithCustomerDniDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.entities.Address;

public interface IAddressMapper {
    AddressResponseDTO entityToResponseDto(Address entity);
    Address requestDtoToEntity(AddressWithCustomerDniDTO requestDto);

    Address requestDtoToEntity(AddressDTO requestDto);
}
