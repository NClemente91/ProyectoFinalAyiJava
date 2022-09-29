package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;

import java.util.List;

public interface IAddressService {
    List<AddressResponseDTO> findAllAddresses();

    AddressResponseDTO findAddressById(Long id);

    AddressResponseDTO createAddress(AddressDTO addressDTO);

    AddressResponseDTO updateAddress(AddressDTO addressDTO);

    void deleteAddressById(Long id);
}
