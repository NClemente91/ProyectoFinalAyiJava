package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.request.AddressWithCustomerDniDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;

public interface IAddressService {

    PagesResponseDTO<AddressResponseDTO> findAllAddresses(Integer page, Integer size);

    AddressResponseDTO findAddressById(Long id);

    AddressResponseDTO createAddress(AddressWithCustomerDniDTO addressDTO);

    AddressResponseDTO updateAddress(AddressDTO addressDTO, Long id);

    void deleteAddressById(Long id);
}
